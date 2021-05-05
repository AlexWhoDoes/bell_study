package org.example.documenttype.dao;

import org.example.documenttype.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeDao extends JpaRepository<DocumentType, Long> {

    DocumentType findDocumentTypeByCode(String code);

    DocumentType findDocumentTypeByDocumentName(String name);

    boolean existsByCode(String code);

    boolean existsByDocumentName(String name);

}
