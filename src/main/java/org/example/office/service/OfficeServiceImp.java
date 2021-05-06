package org.example.office.service;

import org.example.office.dao.OfficeDao;
import org.example.office.dao.OfficeSpecification;
import org.example.office.mapper.OfficeMapper;
import org.example.office.model.Office;
import org.example.office.requestobject.OfficeListRequest;
import org.example.office.requestobject.OfficeSaveRequest;
import org.example.office.requestobject.OfficeUpdateRequest;
import org.example.organization.dao.OrganizationDao;
import org.example.organization.model.Organization;
import org.example.utils.customexception.NoListException;
import org.example.utils.customexception.NoObjectException;
import org.example.utils.customexception.NoOfficeException;
import org.example.utils.customexception.NullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Map;

@Service
public class OfficeServiceImp implements OfficeService {

    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;
    private final OfficeMapper officeMapper;
    private final Logger log;

    @Autowired
    public OfficeServiceImp(OfficeDao officeDao,
                            OrganizationDao organizationDao,
                            OfficeMapper officeMapper) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
        this.officeMapper = officeMapper;
        this.log = LoggerFactory.getLogger(getClass());
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, String>> all(OfficeListRequest officeListRequest) {

        if (officeListRequest == null) {
            log.error("Failed to retrieve list of offices due to null object in method OfficeServiceImp.all()");
            throw new NullException("Failed to retrieve offices due to incorrect input data");
        }

        log.info("A request to retrieve list of offices is received \nRequest: \n" + officeListRequest.toString());

        List<Office> listOffice = officeDao.findAll(OfficeSpecification.getSpecification(officeListRequest));

        if (listOffice.isEmpty()) {
            log.error("Failed to retrieve offices due to parameters " + officeListRequest.toString());
            throw new NoListException("Office", officeListRequest.toString());
        }

        List<Map<String, String>> out = officeMapper.all(listOffice);

        log.info("A list of offices has been retrieved successfully");

        return out;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Map<String, String> getById(Long id) {

        if (id == null) {
            log.error("Failed to retrieve an office due to null object in method OfficeServiceImp.getById()");
            throw new NullException("Failed to retrieve an office due to incorrect input data");
        }

        log.info("A request to get an office by id " + id  + " is received");

        Map<String, String> out;

        try {
            Office office = officeDao.getOne(id);
            out = officeMapper.getById(office);
        } catch (EntityNotFoundException | NoResultException e) {
            log.error("Failed to retrieve an office with id " + id + " from db. An office does not exist.");
            throw new NoObjectException(id, "There is no office with id " + id, "office");
        }

        log.info("An office with id " + id + " has been retrieved successfully");

        return out;

    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional
    public void update(OfficeUpdateRequest officeUpdateRequest) {

        if (officeUpdateRequest == null) {
            log.error("Failed to update an office due to null object in method OfficeServiceImp.update()");
            throw new NullException("Failed to update an office due to incorrect input data");
        }

        log.info("A request to update an office is received \nOffice: \n" + officeUpdateRequest.toString());

        if(!officeDao.existsOfficeById(officeUpdateRequest.getId())) {
            throw new NoOfficeException("office with id "
                    + officeUpdateRequest.getId()
                    + " does not exist", officeUpdateRequest.getId());
        }

        Office office = officeDao.getOne(officeUpdateRequest.getId());
        office.setOfficeName(officeUpdateRequest.getName());
        office.setAddress(officeUpdateRequest.getAddress());

        if (officeUpdateRequest.getPhone() != null) {
            office.setPhone(officeUpdateRequest.getPhone());
        }

        if (officeUpdateRequest.getIsActive() != null) {
            office.setIsActive(officeUpdateRequest.getIsActive());
        }

        officeDao.saveAndFlush(office);

        log.info("An office with id " + officeUpdateRequest.getId() + " has been updated successfully");
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional
    public void save(OfficeSaveRequest officeSaveRequest) {
        if (officeSaveRequest == null) {
            log.error("Failed to save an office due to null object in method OfficeServiceImp.save()");
            throw new NullException("Failed to save an office due to incorrect input data");
        }
        log.info("A request to save an office is received \nOffice: \n" + officeSaveRequest.toString());

        Organization organization = organizationDao.getById(officeSaveRequest.getOrgId());
        Office office = new Office();
        office.setOrganization(organization);

        if(officeSaveRequest.getName() != null) {
            office.setOfficeName(officeSaveRequest.getName());
        }

        if(officeSaveRequest.getAddress() != null) {
            office.setAddress(officeSaveRequest.getAddress());
        }

        if (officeSaveRequest.getPhone() != null) {
            office.setPhone(officeSaveRequest.getPhone());
        }

        if (officeSaveRequest.getIsActive() != null) {
            office.setIsActive(officeSaveRequest.getIsActive());
        }

        officeDao.saveAndFlush(office);

        log.info("An organization:\n" + officeSaveRequest.toString() + "\nhas been saved successfully");

    }
}
