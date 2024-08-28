package com.ejun.badminton.utils.Exceptions;

import com.ejun.badminton.utils.ErrorCode;
import lombok.Getter;
import org.springframework.validation.Errors;

import java.io.Serial;

@Getter
public class InvalidParameterException extends CustomException {

    @Serial
    private static final long serialVersionUID = -2116671122895194101L;

    private final Errors errors;

    public InvalidParameterException(Errors errors) {
        super(ErrorCode.INVALID_PARAMETER);
        this.errors = errors;
    }

}