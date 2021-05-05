package org.example.citizenship.controller;

import org.example.citizenship.service.ServiceCitizenship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/countries")
public class citizenshipController {
    private final ServiceCitizenship serviceCitizenship;

    @Autowired
    public citizenshipController(ServiceCitizenship serviceCitizenship) {
        this.serviceCitizenship = serviceCitizenship;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Map<String, String>>> all() {
        return new ResponseEntity<>(serviceCitizenship.all(), HttpStatus.OK);
    }
}
