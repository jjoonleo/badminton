package com.ejun.badminton.utils.Exceptions;

import com.ejun.badminton.utils.ErrorCode;

import java.io.Serial;

public class UserNotFoundException extends CustomException {

    @Serial
    private static final long serialVersionUID = -2116671122895194101L;

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
