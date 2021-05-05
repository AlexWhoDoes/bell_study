package org.example.organization.controller;

import org.example.organization.requestobject.OrganizationListRequest;
import org.example.organization.requestobject.OrganizationSaveRequest;
import org.example.organization.requestobject.OrganizationUpdateRequest;
import org.example.organization.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "/list", method = POST)
    List<Map<String, String>> all(@Valid @RequestBody OrganizationListRequest organizationListRequest) {
        return organizationService.all(organizationListRequest);

    }

    @RequestMapping(value = "/{id}", method = GET)
    Map<String, String> getById(@PathVariable("id") Long id) {
        return organizationService.getById(id);
    }

    @RequestMapping(value = "/update", method = POST)
    void update(@Valid @RequestBody OrganizationUpdateRequest organizationUpdateRequest) {
        organizationService.update(organizationUpdateRequest);
    }

    @RequestMapping(value = "/save", method = POST)
    void save (@Valid @RequestBody OrganizationSaveRequest organizationSaveRequest) {
        organizationService.save(organizationSaveRequest);
    }

}
