package org.example.organization.service;

import org.example.organization.requestobject.OrganizationListRequest;
import org.example.organization.requestobject.OrganizationSaveRequest;
import org.example.organization.requestobject.OrganizationUpdateRequest;

import java.util.List;
import java.util.Map;

public interface OrganizationService {

    /**
     * Get all of organizations according to parameter from request body
     * @param organizationListRequest
     * @return
     */
    List<Map<String, String>> all (OrganizationListRequest organizationListRequest);

    /**
     * Get organization by id
     * @param id
     * @return
     */
    Map<String, String> getById(Long id);

    /**
     * Update an existing organization
     * @param organizationUpdateRequest
     * @return
     */
    void update(OrganizationUpdateRequest organizationUpdateRequest);

    /**
     * Add a new organization to db
     * @param organizationSaveRequest
     * @return
     */
    void save(OrganizationSaveRequest organizationSaveRequest);
}
