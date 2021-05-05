package org.example.utils.customexception;

import lombok.Data;

@Data
public class NoObjectException extends CustomException{
    private Long id;
    private String massage;
    private String objectName;

    public NoObjectException(Long id, String massage, String objectName) {
        super();
        this.id = id;
        this.massage = massage;
        this.objectName = objectName;
    }
}
