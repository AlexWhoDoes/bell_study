package org.example.office.service;

import org.example.office.requestobject.OfficeListRequest;
import org.example.office.requestobject.OfficeSaveRequest;
import org.example.office.requestobject.OfficeUpdateRequest;
import java.util.List;
import java.util.Map;

public interface OfficeService {

    /**
     * Get all of offices according to parameter from request body
     * @param officeListRequest
     * @return
     */
    List<Map<String, String>> all(OfficeListRequest officeListRequest);

    /**
     * Get office by id
     * @param id
     * @return
     */
    Map<String, String> getById(Long id);

    /**
     * Update an existing organization
     * @param officeUpdateRequest
     * @return
     */
    void update(OfficeUpdateRequest officeUpdateRequest);


    /**
     * Add a new organization to db
     * @param OfficeSaveRequest
     * @return
     */
    void save(OfficeSaveRequest OfficeSaveRequest);
}
