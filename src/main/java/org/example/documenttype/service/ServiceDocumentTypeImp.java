package org.example.documenttype.service;

import org.example.documenttype.dao.DocumentTypeDao;
import org.example.documenttype.model.DocumentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceDocumentTypeImp implements ServiceDocumentType {
    private final DocumentTypeDao documentTypeDao;
    private final Logger log;

    @Autowired
    public ServiceDocumentTypeImp(DocumentTypeDao documentTypeDao) {
        this.documentTypeDao = documentTypeDao;
        this.log = LoggerFactory.getLogger(getClass());
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, String>> all() {

        log.info("A request to retrieve a list of document types is received");

        List<DocumentType> documentTypeList = documentTypeDao.findAll();
        List<Map<String, String>> out = mapper(documentTypeList);

        log.info("A list of document types  has been retrieved successfully");

        return out;
    }

    private List<Map<String, String>> mapper(List<DocumentType> documentTypeList) {
        List<Map<String, String>> out = new ArrayList<>();
        for (DocumentType documentType: documentTypeList) {

            Map<String, String> responseBody = new LinkedHashMap<>();
            responseBody.put("name", documentType.getDocumentName());
            responseBody.put("code", documentType.getCode());
            out.add(responseBody);
        }
        return out;
    }
}
