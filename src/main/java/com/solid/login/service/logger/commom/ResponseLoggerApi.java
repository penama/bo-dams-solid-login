package com.solid.login.service.logger.commom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseLoggerApi {

    private String status;
    private String method;
    private String log_value;

    public String toString(){
        return "[status=" + status + ", method=" + method + ", log_value=" + log_value + "]";
    }
}
