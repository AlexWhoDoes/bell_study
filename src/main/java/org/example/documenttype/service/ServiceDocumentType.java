package org.example.documenttype.service;

import org.example.documenttype.documenttypeview.DocumentTypeView;
import java.util.List;


public interface ServiceDocumentType {

    /**
     * Return list of existing document types in a response representation
     * @return
     */
    List<DocumentTypeView> all();
}
