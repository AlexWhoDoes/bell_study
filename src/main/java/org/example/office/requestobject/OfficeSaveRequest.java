package org.example.office.requestobject;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OfficeSaveRequest {

    @NotNull(message = "Field orgId should not be empty")
    private Long orgId;
    @Size(max = 50)
    private String name;
    @Size(max = 255)
    private String address;
    @Size(max = 20)
    private String phone;
    private Boolean isActive;
}
