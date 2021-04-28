package org.example.user.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto{

    public Long id;
    public String firstName;
    public String secondName;
    public String middleName;
    public String occupation;
    public String phone;

    public String documentName;  //document -> documentType
    public String documentNumber; //document
    public Date documentDate; //document

    public String citizenshipName; // Citizenship;
    public String code; // Citizenship;

    public Boolean isIdentified;

}
