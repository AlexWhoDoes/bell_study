package org.example.citizenship.controller;

import org.example.citizenship.ctizenshipview.CitizenshipView;
import org.example.citizenship.service.ServiceCitizenship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/countries")
public class CitizenshipController {
    private final ServiceCitizenship serviceCitizenship;

    @Autowired
    public CitizenshipController(ServiceCitizenship serviceCitizenship) {
        this.serviceCitizenship = serviceCitizenship;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<CitizenshipView> all() {
        return serviceCitizenship.all();
    }
}
