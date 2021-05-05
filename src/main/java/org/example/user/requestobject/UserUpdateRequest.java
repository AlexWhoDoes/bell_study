package org.example.user.requestobject;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserUpdateRequest {

    @NotNull(message = "Field id should not be empty")
    private Long id;
    private Long officeId;
    @Size(max = 50)
    @NotEmpty(message = "Field firstName should not be empty")
    private String firstName;
    @Size(max = 50)
    private String secondName;
    @Size(max = 50)
    private String middleName;
    @Size(max = 50)
    @NotEmpty(message = "Field position should not be empty")
    private String position;
    @Size(max = 20)
    private String phone;
    @Size(max = 50)
    private String docName;
    @Size(max = 10)
    private String docNumber;
    private Date docDate;
    @Size(max = 3)
    private String citizenshipCode;
    private Boolean isIdentified;

}
