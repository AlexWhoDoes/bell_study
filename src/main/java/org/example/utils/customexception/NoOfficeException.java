package org.example.utils.customexception;

import lombok.Data;

@Data
public class NoOfficeException extends CustomException {
    private Long officeId;
    private String exceptionInfo;

    public NoOfficeException(String exceptionInfo, Long officeId) {
        super();
        this.exceptionInfo = exceptionInfo;
        this.officeId = officeId;
    }
}
