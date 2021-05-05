package org.example.document.dao;

import org.example.document.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentDao extends JpaRepository<Document, Long> {
    boolean existsById(Long id);

}
