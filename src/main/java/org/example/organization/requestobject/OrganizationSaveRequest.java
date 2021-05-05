package org.example.organization.requestobject;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class OrganizationSaveRequest implements OrganizationSaveOrUpdate { // может добавить ограничения на количество символов как в бд

    @Size(max = 50)
    @NotEmpty(message = "Field name should not be empty")
    private String name;
    @Size(max = 150)
    @NotEmpty(message = "Field fullName should not be empty")
    private String fullName;
    @Size(max = 10)
    @NotEmpty(message = "Field inn should not be empty")
    private String inn;
    @Size(max = 9)
    @NotEmpty(message = "Field kpp should not be empty")
    private String kpp;
    @Size(max = 255)
    @NotEmpty(message = "Field address should not be empty")
    private String address;
    @Size(max = 20)
    private String phone;
    private Boolean isActive;

}
