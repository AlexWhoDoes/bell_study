package org.example.user.requestobject;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserListRequest {

    @NotNull(message = "Field officeId should not be empty")
    private Long officeId;
    @Size(max = 50)
    private String firstName;
    @Size(max = 50)
    private String secondName;
    @Size(max = 50)
    private String middleName;
    @Size(max = 50)
    private String position;
    @Size(max = 3)
    private String docCode;
    @Size(max = 3)
    private String citizenshipCode;

}
