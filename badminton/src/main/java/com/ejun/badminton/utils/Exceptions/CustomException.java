package com.ejun.badminton.utils.Exceptions;

import com.ejun.badminton.utils.ErrorCode;
import lombok.Getter;

import java.io.Serial;

@Getter
public class CustomException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}