package org.example.common.security.exception;

import lombok.Getter;
import org.example.common.core.enums.ResultCode;

@Getter
public class ServiceException extends RuntimeException {

    private ResultCode resultCode;

    public ServiceException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
