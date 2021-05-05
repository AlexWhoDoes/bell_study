package org.example.utils.customexception;

import lombok.Data;

@Data
public class NullException extends CustomException {
    private String exceptionInfo;
    private RuntimeException runtimeException;

    public NullException(String exceptionInfo) {
        super();
        this.exceptionInfo = exceptionInfo;
    }
}
