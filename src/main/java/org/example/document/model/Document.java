package org.example.document.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.typeofdocument.model.DocumentType;
import org.example.user.model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MapsId;
import javax.persistence.FetchType;


import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Document")
public class Document {

    @Id
    private Long id;

    /**
     * Hibernate internal service field
     */
    @Version
    private Integer version;

    @ManyToOne
    @JoinColumn(name = "doc_type_id")
    private DocumentType documentType;

    @Column(name = "document_date")
    @Temporal(TemporalType.DATE)
    private Date documentDate;

    @Column(name = "document_number")
    private String documentNumber;


    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;




}
