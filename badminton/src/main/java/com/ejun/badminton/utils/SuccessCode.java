package com.ejun.badminton.utils;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    CREATED(201, "S001", "Successfully Created"),
    JOINED(200, "S002", "Successfully Joined"),
    SUCCESS(200, "S003", "Successfully Success"),
    UPDATED(200, "S004", "Successfully Updated"),
    DELETE(200, "S005", "Successfully Deleted"),
    CLUB_CREATED(201, "S006", "Successfully Created Club"),
    JOIN_CLUB(200, "S007", "Successfully Joined Club"),
    LEFT_CLUB(200, "S008", "Successfully Left Club"),
    CLUB_DELETED(200, "S009", "Successfully Deleted Club"),
    CLUB_UPDATED(200, "S010", "Successfully Updated Club"),
    CLUB_LOADED(200, "S011", "Successfully Loaded Club"),
    SIGN_UP(201, "S012", "Successfully Signed Up"),
    SIGN_IN(200, "S013", "Successfully Signed In"),;


    private final int status;
    private final String code;
    private final String message;
}
