package org.example.utils.customexception;

import lombok.Data;

@Data
public class NoCitizenshipException extends CustomException {
    private String citizenshipCode;
    private String exceptionInfo;

    public NoCitizenshipException(String exceptionInfo, String citizenshipCode) {
        super();
        this.exceptionInfo = exceptionInfo;
        this.citizenshipCode = citizenshipCode;
    }
}
