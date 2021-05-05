package org.example.organization.dao;

import org.example.organization.model.Organization;
import org.example.organization.requestobject.OrganizationListRequest;

import java.util.List;

public interface OrganizationDao {
    /**
     * Get all of organizations according to parameter from request body
     * @param organizationListRequest
     * @return
     */
    List<Organization> all(OrganizationListRequest organizationListRequest);

    /**
     * Get organization by id
     * @param id
     * @return
     */
    Organization getById(Long id);

    /**
     * Add or update an organization
     * @param organization
     * @return
     */
    void addOrUpdate(Organization organization);

}


