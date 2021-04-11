package org.example.organization.model;

import org.example.office.model.Office;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.user.model.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Version;
import javax.persistence.OneToMany;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
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

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(
            mappedBy = "organization",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Office> offices;


}
