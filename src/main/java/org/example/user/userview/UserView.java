package org.example.user.userview;

import lombok.Data;

@Data
public class UserView {

    private Long id;
    private Long officeId;
    private String firstName;
    private String secondName;
    private String middleName;
    private String position;
    private String phone;
    private String docCode;
    private String docName;
    private String docNumber;
    private String docDate;
    private String citizenshipCode;
    private String citizenshipName;
    private String isIdentified;
}
