package org.example.user.requestobject;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserSaveRequest {

    @NotNull(message = "Field officeId should not be empty")
    private Long officeId;
    @Size(max = 50)
    @NotEmpty(message = "Field firstName should not be empty")
    private String firstName;
    @Size(max = 50)
    private String secondName;
    @Size(max = 50)
    private String middleName;
    @Size(max = 20)
    private String phone;
    @NotEmpty(message = "Field position should not be empty")
    @Size(max = 50)
    private String position;
    @Size(max = 3)
    private String docCode;
    @Size(max = 50)
    private String docName;
    @Size(max = 10)
    private String docNumber;
    private Date docDate;
    @Size(max = 3)
    private String citizenshipCode;
    private Boolean isIdentified;

}


