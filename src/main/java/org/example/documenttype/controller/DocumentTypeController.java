package org.example.documenttype.controller;

import org.example.documenttype.documenttypeview.DocumentTypeView;
import org.example.documenttype.service.ServiceDocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("docs")
public class DocumentTypeController {
    private final ServiceDocumentType serviceDocumentType;

    @Autowired
    public DocumentTypeController(ServiceDocumentType serviceDocumentType) {
        this.serviceDocumentType = serviceDocumentType;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<DocumentTypeView> all() {
        return serviceDocumentType.all();
    }
}
