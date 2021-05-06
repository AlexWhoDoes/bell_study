package org.example.organization.service;

import org.example.organization.dao.OrganizationDao;
import org.example.organization.mapper.OrganizationMapper;
import org.example.organization.model.Organization;
import org.example.organization.organizationview.OrganizationView;
import org.example.organization.organizationview.OrganizationViewShort;
import org.example.organization.requestobject.OrganizationListRequest;
import org.example.organization.requestobject.OrganizationSaveOrUpdate;
import org.example.organization.requestobject.OrganizationSaveRequest;
import org.example.organization.requestobject.OrganizationUpdateRequest;
import org.example.utils.customexception.NoListException;
import org.example.utils.customexception.NullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class OrganizationServiceImp implements OrganizationService {

    private final OrganizationDao organizationDao;
    private final OrganizationMapper organizationMapper;
    private final Logger log;

    @Autowired
    public OrganizationServiceImp(OrganizationDao organizationDao,
                                  OrganizationMapper organizationMapper) {
        this.organizationDao = organizationDao;
        this.organizationMapper = organizationMapper;
        this.log = LoggerFactory.getLogger(getClass());
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationViewShort> all(OrganizationListRequest organizationListRequest) {

        if (organizationListRequest == null) {
            log.error("Failed to retrieve list of organizations due to null object in method OrganizationServiceImp.all()");
            throw new NullException("Failed to retrieve users due to incorrect input data");
        }

        log.info("A request to retrieve list of organizations is received \nRequest: \n" + organizationListRequest.toString());

        List<Organization> organizationList = organizationDao.all(organizationListRequest);

        if (organizationList.isEmpty()) {
            log.error("Failed to retrieve organizations due to parameters " + organizationListRequest.toString());
            throw new NoListException("Organization", organizationListRequest.toString());
        }

        List<OrganizationViewShort> out = organizationMapper.all(organizationList);

        log.info("A list of organizations has been retrieved successfully");

        return out;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationView getById(Long id) {

        if (id == null) {
            log.error("Failed to retrieve an organization due to null object in method OrganizationServiceImp.getById()");
            throw new NullException("Failed to retrieve an organization due to incorrect input data");
        }

        log.info("A request to get an organization by id " + id  + " is received");

        Organization organization = organizationDao.getById(id);
        OrganizationView out  = organizationMapper.getById(organization);

        log.info("An organization with id " + id + " has been retrieved successfully");

        return out;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional
    public void update(OrganizationUpdateRequest organizationUpdateRequest) {

        if (organizationUpdateRequest == null) {
            log.error("Failed to update an organization due to null object in method OrganizationServiceImp.update()");
            throw new NullException("Failed to update an organization due to incorrect input data");
        }

        log.info("A request to update an organization is received \nOrganization: \n" + organizationUpdateRequest.toString());

        Organization organization = organizationDao.getById(organizationUpdateRequest.getId());
        organizationInitializer(organization, organizationUpdateRequest);
        organizationDao.addOrUpdate(organization);

        log.info("An organization with id " + organizationUpdateRequest.getId() + " has been updated successfully");
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional
    public void save(OrganizationSaveRequest organizationSaveRequest) {
        if (organizationSaveRequest == null) {
            log.error("Failed to save an organization due to null object in method OrganizationServiceImp.save()");
            throw new NullException("Failed to save an organization due to incorrect input data");
        }
        log.info("A request to save an organization is received \nObject: \n" + organizationSaveRequest.toString());

        Organization organization = new Organization();
        organizationInitializer(organization, organizationSaveRequest);
        organizationDao.addOrUpdate(organization);

        log.info("An organization:\n" + organizationSaveRequest.toString() + "\nhas been saved successfully");
    }

    private void organizationInitializer(Organization organization, OrganizationSaveOrUpdate organizationRequestObject) {
        organization.setShortName(organizationRequestObject.getName());
        organization.setFullName(organizationRequestObject.getFullName());
        organization.setInn(organizationRequestObject.getInn());
        organization.setKpp(organizationRequestObject.getKpp());
        organization.setAddress(organizationRequestObject.getAddress());

        if (organizationRequestObject.getPhone() != null) {
            organization.setPhone(organizationRequestObject.getPhone());
        }

        if (organizationRequestObject.getIsActive() != null) {
            organization.setIsActive(organizationRequestObject.getIsActive());
        }
    }
}
