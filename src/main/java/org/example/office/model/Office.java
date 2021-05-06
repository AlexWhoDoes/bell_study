package org.example.office.model;

import lombok.Data;
import org.example.organization.model.Organization;
import org.example.user.model.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Set;

@Data
@Entity
@Table(name = "Office")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Hibernate internal service field
     */
    @Version
    private Integer version;

    @Column(name = "office_name", length = 50)
    private String officeName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organisation_id")
    private Organization organization;

    @OneToMany(
            mappedBy = "office",
            fetch = FetchType.LAZY
    )
    private Set<User> user;

}
