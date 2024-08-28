package com.ejun.badminton.utils.Exceptions;

import com.ejun.badminton.utils.ErrorCode;

import java.io.Serial;

public class ClubNotFoundException extends CustomException{

    @Serial
    private static final long serialVersionUID = -2116671122895194101L;

    public ClubNotFoundException() {
        super(ErrorCode.CLUB_NOT_FOUND);

    }
}
