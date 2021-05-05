package org.example.documenttype.service;

import java.util.List;
import java.util.Map;

public interface ServiceDocumentType {

    /**
     * Return list of existing document types in a response representation
     * @return
     */
    List<Map<String, String>> all();
}
