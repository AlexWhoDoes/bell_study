package org.example.organization.requestobject;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class OrganizationListRequest {

    @Size(max = 50)
    @NotEmpty(message = "Field name should not be empty")
    private String name;
    @Size(max = 10)
    private String inn;
    private Boolean isActive;

}
