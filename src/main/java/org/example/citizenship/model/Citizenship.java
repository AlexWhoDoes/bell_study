package org.example.citizenship.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.GenerationType;

@Data
@Entity
@Table(name = "Citizenship")
public class Citizenship {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    /**
     * Hibernate internal service field
     */
    @Version
    private Integer version;

    @Column(name = "code")
    private String code;

    @Column(name = "citizenship_name")
    private String citizenshipName;

}
