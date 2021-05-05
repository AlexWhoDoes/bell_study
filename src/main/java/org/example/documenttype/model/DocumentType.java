package org.example.documenttype.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Version;


@Data
@Entity
@Table(name = "Document_type")
public class DocumentType {  //make using springData DOUBLETHINK HOW TO

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Hibernate internal service field
     */
    @Version
    private Integer version;

    @Column(name = "document_name", length = 50)
    private String documentName;

    @Column(name = "code", length = 3)
    private String code;

}
