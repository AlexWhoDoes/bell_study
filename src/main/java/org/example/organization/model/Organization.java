package org.example.organization.model;

import lombok.Data;
import org.example.office.model.Office;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Version;
import javax.persistence.OneToMany;

@Data
@NamedQueries({
        @NamedQuery(
                name = "OrganizationGetById",
                query = "SELECT p FROM Organization p WHERE p.id =: ORGANIZATION_ID"
        )
})

@Entity
@Table(name = "Organization")
public class Organization {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Hibernate internal service field
     */
    @Version
    private Integer version;

    @Column(name = "short_name", nullable = false, length = 50)
    private String shortName;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(name = "inn", nullable = false, length = 10)
    private String inn;

    @Column(name = "kpp", nullable = false, length = 9)
    private String kpp;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(
            mappedBy = "organization",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Office> offices;


}
