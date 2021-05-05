package org.example.office.requestobject;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OfficeUpdateRequest {

    @NotNull(message = "Field id should not be empty")
    private Long id;
    @Size(max = 50)
    @NotEmpty(message = "Field name should not be empty")
    private String name;
    @Size(max = 255)
    @NotEmpty(message = "Field address should not be empty")
    private String address;
    @Size(max = 20)
    private String phone;
    private Boolean isActive;

}
