package org.example.utils.customexception;

import lombok.Data;

@Data
public class NoListException extends CustomException {
    private String object;
    private String param;

    public NoListException(String object, String param) {
        super();
        this.object = object;
        this.param = param;
    }
}
