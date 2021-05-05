package org.example.office.controller;

import org.example.office.requestobject.OfficeListRequest;
import org.example.office.requestobject.OfficeSaveRequest;
import org.example.office.requestobject.OfficeUpdateRequest;
import org.example.office.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/office")
public class officeController {
    private final OfficeService officeService;

    @Autowired
    public officeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @RequestMapping(value = "/list", method = POST)
    List<Map<String, String>> all(@Valid @RequestBody OfficeListRequest officeListRequest) {
        return officeService.all(officeListRequest);
    }

    @RequestMapping(value = "/{id}", method = GET)
    Map<String, String> getById(@PathVariable("id") Long id) {
        return officeService.getById(id);
    }

    @RequestMapping(value = "/update", method = POST)
    void update(@Valid @RequestBody OfficeUpdateRequest officeUpdateRequest) {
        officeService.update(officeUpdateRequest);
    }

    @RequestMapping(value = "/save", method = POST)
    void save (@Valid @RequestBody OfficeSaveRequest OfficeSaveRequest) {
        officeService.save(OfficeSaveRequest);
    }

}
