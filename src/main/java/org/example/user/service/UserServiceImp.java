package org.example.user.service;

import lombok.Data;
import org.example.user.requestobject.UserSaveRequest;
import org.example.utils.customexception.NoCitizenshipException;
import org.example.utils.customexception.NoDocTypeException;
import org.example.utils.customexception.NoDocumentException;
import org.example.utils.customexception.NoListException;
import org.example.utils.customexception.NoOfficeException;
import org.example.utils.customexception.NullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.citizenship.dao.CitizenshipDao;
import org.example.citizenship.model.Citizenship;
import org.example.document.dao.DocumentDao;
import org.example.document.model.Document;
import org.example.office.dao.OfficeDao;
import org.example.documenttype.dao.DocumentTypeDao;
import org.example.documenttype.model.DocumentType;
import org.example.user.dao.UserDao;
import org.example.user.requestobject.UserListRequest;
import org.example.user.requestobject.UserUpdateRequest;
import org.example.user.mapper.UserMapper;
import org.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Data
public class UserServiceImp implements UserService {

    private UserMapper userMapper;
    private UserDao userDao;
    private DocumentTypeDao documentTypeDao;
    private DocumentDao documentDao;
    private OfficeDao officeDao;
    private CitizenshipDao citizenshipDao;
    private final Logger log;


    @Autowired
    public UserServiceImp(UserMapper userMapper,
                          UserDao userDao,
                          DocumentTypeDao documentTypeDao,
                          DocumentDao documentDao,
                          OfficeDao officeDao,
                          CitizenshipDao citizenshipDao
                          ) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.documentDao = documentDao;
        this.documentTypeDao = documentTypeDao;
        this.officeDao = officeDao;
        this.citizenshipDao = citizenshipDao;
        this.log = LoggerFactory.getLogger(getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, String>> all(UserListRequest userListRequest) {

        if (userListRequest == null) {
            log.error("Failed to retrieve users due to null object in method UserServiceImp.all()");
            throw new NullException("Failed to retrieve users due to incorrect input data");
        }

        log.info("A request to retrieve list of users is received \nRequest: \n" + userListRequest.toString());

        List<User> userList = userDao.all(userListRequest);
        if (userList.isEmpty()) {
            log.error("Failed to retrieve users due to parameters " + userList.toString());
            throw new NoListException("User", userListRequest.toString());
        }
        List<Map<String, String>> out = userMapper.all(userList);
        log.info("A list of users has been retrieved successfully");

        return out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, String> getById(Long id) { //check null and for what this method need a comment

        if (id == null) {
            log.error("Failed to retrieve a user due to null object in method UserServiceImp.getById()");
            throw new NullException("Failed to retrieve a user due to incorrect input data");
        }

        log.info("A request to get a user by id " + id  + " is received");

        User user = userDao.getById(id);
        Map<String, String> out = userMapper.getById(user);;

        log.info("A user with id " + id + " has been retrieved successfully");

        return out;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional
    public void save (UserSaveRequest userSaveRequest) {
        if (userSaveRequest == null) {
            log.error("Failed to save a user due to null object in method UserServiceImp.save()");
            throw new NullException("Failed to save a user due to incorrect input data");
        }

        log.info("A request to save a user is received \nObject: \n" + userSaveRequest.toString());

        User user = userMapper.save(userSaveRequest);

        if(userSaveRequest.getOfficeId() != null) {
            if(!officeDao.existsOfficeById(userSaveRequest.getOfficeId())) {

                log.error("Failed to save object due to incorrect office id\n"
                        + userSaveRequest.toString()
                        + "\nin method UserServiceImp.save(). Office does not exist");

                throw new NoOfficeException("office with id "
                        + userSaveRequest.getOfficeId()
                        + " does not exist", userSaveRequest.getOfficeId());
            }
                        user.setOffice(officeDao.getOne(userSaveRequest.getOfficeId()));
        }

        if(userSaveRequest.getCitizenshipCode() != null) {
            if(!citizenshipDao.existsByCode(userSaveRequest.getCitizenshipCode())) {

                log.error("Failed to save object due to incorrect citizenship code\n"
                        + userSaveRequest.toString()
                        + "\nin method UserServiceImp.save(). Citizenship does not exist");

                throw new NoCitizenshipException("citizenship with code "
                        + userSaveRequest.getCitizenshipCode()
                        + " does not exist", userSaveRequest.getCitizenshipCode());
            }
            user.setCitizenship(citizenshipDao.findCitizenshipByCode(userSaveRequest.getCitizenshipCode()));
        }

        if (isDocumentNeeded(userSaveRequest)) {
            Document document = documentBuilder(userSaveRequest);
            Long id = userDao.addOrUpdate(user);
            document.setId(id);
            document.setUser(user);
            documentDao.save(document);
            user.setDocument(document);
        }

        userDao.addOrUpdate(user);
        log.info("A user:\n" + userSaveRequest.toString() + "\nhas been saved successfully");

    }

    /**
     *
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional
    public void update(UserUpdateRequest userUpdateRequest) {
        if (userUpdateRequest == null) {
            log.error("Failed to update a user due to null object in method UserServiceImp.save()");
            throw new NullException("Failed to save a user due to incorrect input data");
        }

        log.info("A request to update a user is received \nUser: \n" + userUpdateRequest.toString());
        User user = userDao.getById(userUpdateRequest.getId());


        if (userUpdateRequest.getOfficeId() != null) {
            if (!officeDao.existsOfficeById(userUpdateRequest.getOfficeId())) {

                log.error("Failed to update object due to incorrect office id\n"
                        + userUpdateRequest.toString()
                        + "\nin method UserServiceImp.update(). Office does not exist");

                throw new NoOfficeException("office with id "
                        + userUpdateRequest.getOfficeId() +
                        " does not exist",
                        userUpdateRequest.getOfficeId());
            }
            user.setOffice(officeDao.getOne(userUpdateRequest.getOfficeId()));
        }

        if (userUpdateRequest.getFirstName() != null) {
            user.setFirstName(userUpdateRequest.getFirstName());
        }

        if (userUpdateRequest.getSecondName() != null) {
            user.setSecondName(userUpdateRequest.getSecondName());
        }

        if (userUpdateRequest.getPosition() != null) {
            user.setSecondName(userUpdateRequest.getPosition());
        }

        if (userUpdateRequest.getPhone() != null) {
            user.setPhone(userUpdateRequest.getPhone());
        }

        if (userUpdateRequest.getDocName() != null) {
            if (documentTypeDao.existsByDocumentName(userUpdateRequest.getDocName())) {
                DocumentType documentType = documentTypeDao.findDocumentTypeByDocumentName(userUpdateRequest.getDocName());
                Document document = user.getDocument();
                document.setDocumentType(documentType);
                user.setDocument(document);
            } else {
                log.error("app tried to get document type with name "
                        + userUpdateRequest.getDocName()
                        + " in method UserServiceImp.update() but document type does not exist");

                throw new NoDocTypeException("document type with name "
                        + userUpdateRequest.getDocName()
                        + " does not exist",
                        userUpdateRequest.getDocName());
            }
        }

        if (userUpdateRequest.getDocDate() != null) {

            if (!documentDao.existsById(userUpdateRequest.getId())) {
                log.error("app tried to update user document with id "
                        + userUpdateRequest.getId()
                        + " in method UserServiceImp.update() but document does not exist");

                throw new NoDocumentException(userUpdateRequest.getId(), "A user with id "
                        + userUpdateRequest.getId()
                        + " do not have a document. Could not update document since it does not exist");
            }

            Document document = user.getDocument();
            document.setDocumentDate(userUpdateRequest.getDocDate());
            user.setDocument(document);

        }

        if (userUpdateRequest.getDocNumber() != null) {

            if (!documentDao.existsById(userUpdateRequest.getId())) {
                log.error("app tried to update user document with id "
                        + userUpdateRequest.getId()
                        + " in method UserServiceImp.update() but document does not exist");

                throw new NoDocumentException(userUpdateRequest.getId(), "A user with id "
                        + userUpdateRequest.getId()
                        + " do not have a document. Could not update document since it does not exist");
            }

            Document document = user.getDocument();
            document.setDocumentNumber(userUpdateRequest.getDocNumber());
            user.setDocument(document);
        }

        if (userUpdateRequest.getCitizenshipCode() != null) {
            if (citizenshipDao.existsByCode(userUpdateRequest.getCitizenshipCode())) {
                Citizenship citizenship = citizenshipDao.findCitizenshipByCode(userUpdateRequest.getCitizenshipCode());
                user.setCitizenship(citizenship);
            } else {

                log.error("Failed to save object due to incorrect citizenship code\n"
                        + userUpdateRequest.toString()
                        + "\nin method UserServiceImp.save(). Citizenship does not exist");

                throw new NoCitizenshipException("citizenship with code "
                        + userUpdateRequest.getCitizenshipCode()
                        + " does not exist", userUpdateRequest.getCitizenshipCode());
            }
        }

        if (userUpdateRequest.getIsIdentified() != null) {
            user.setIsIdentified(userUpdateRequest.getIsIdentified());
        }

        userDao.addOrUpdate(user);

        log.info("A user with id " + userUpdateRequest.getId() + " has been updated successfully");
    }

    private boolean isDocumentNeeded(UserSaveRequest userSaveRequest) {
        if ((userSaveRequest.getDocCode() != null) || (userSaveRequest.getDocName() != null)) {
            return ((userSaveRequest.getDocDate() != null) && (userSaveRequest.getDocNumber() != null));
        }
        return false;
    }

    private Document documentBuilder(UserSaveRequest userSaveRequest) {

        if ((documentTypeDao.existsByCode(userSaveRequest.getDocCode()))
                || (documentTypeDao.existsByDocumentName(userSaveRequest.getDocName()))) {

            DocumentType documentType = documentTypeDao.findDocumentTypeByCode(userSaveRequest.getDocCode());

            if (documentType == null) {
                documentType = documentTypeDao.findDocumentTypeByDocumentName(userSaveRequest.getDocName());
            }

            Document document = new Document();
            document.setDocumentType(documentType);
            document.setDocumentDate(userSaveRequest.getDocDate());
            document.setDocumentNumber(userSaveRequest.getDocNumber());

            return document;

        } else {

            log.error("app tried to get document type with code and/or name "
                    + userSaveRequest.getDocCode()
                    + " "
                    + userSaveRequest.getDocName()
                    + " in method UserServiceImp.save() but document type does not exist");

            NoDocTypeException noDocTypeException = new NoDocTypeException("document type with requested code and/or name does not exist",
                    userSaveRequest.getDocName());
            noDocTypeException.setDocCode(userSaveRequest.getDocCode());

            throw noDocTypeException;

        }
    }
}
