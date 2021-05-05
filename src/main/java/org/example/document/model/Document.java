package org.example.document.model;

import lombok.Data;
import org.example.documenttype.model.DocumentType;
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

@Data
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

    @Column(name = "document_date")
    @Temporal(TemporalType.DATE)
    private Date documentDate;

    @Column(name = "document_number", length = 10)
    private String documentNumber;

    @ManyToOne
    @JoinColumn(name = "doc_type_id")
    private DocumentType documentType;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

}
