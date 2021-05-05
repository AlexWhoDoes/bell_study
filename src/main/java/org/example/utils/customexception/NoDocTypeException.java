package org.example.utils.customexception;

import lombok.Data;

@Data
public class NoDocTypeException extends CustomException {
    private String docCode;
    private String docName;
    private String exceptionInfo;

    public NoDocTypeException(String exceptionInfo, String docName) {
        super();
        this.exceptionInfo = exceptionInfo;
        this.docName = docName;
    }


}
