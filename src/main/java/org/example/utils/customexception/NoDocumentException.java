package org.example.utils.customexception;

import lombok.Data;

@Data
public class NoDocumentException extends CustomException {
    private Long documentId;
    private Long userId;
    private String exceptionInfo;

    public NoDocumentException(Long id, String exceptionInfo) {
        super();
        this.documentId = id;
        this.userId = id;
        this.exceptionInfo = exceptionInfo;
    }
}
