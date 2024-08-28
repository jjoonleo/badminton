package com.ejun.badminton.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_PARAMETER(400, "E004", "Invalid Request Data"),
    INVALID_JSON(400, "E005", "Invalid JSON"),
    BAD_CREDENTIALS(401, "E001", "Bad Credentials"),
    EXPIRED_JWT(401, "E002", "Expired JWT"),
    INVALID_JWT_SIGNATURE(401, "E003", "Invalid JWT Signature"),
    ENDPOINT_NOT_FOUND(404, "E006", "Endpoint Not Found"),
    CLUB_NOT_FOUND(404,"E007" , "Club Not Found"),
    USER_NOT_FOUND(404, "E008", "User Not Found");


    private final int status;
    private final String code;
    private final String message;
}
