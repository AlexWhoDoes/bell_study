package org.example.organization.organizationview;

import lombok.Data;

@Data
public class OrganizationView {

    private Long id;
    private String name;
    private String fullName;
    private String inn;
    private String kpp;
    private String address;
    private String phone;
    private String isActive;

}
