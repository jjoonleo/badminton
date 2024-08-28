package com.ejun.badminton.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter // All lombok annotations
@Getter
@ToString
public class RestResponse<T> {

    private int status;
    private String message;

    private T data;

    public static <T> RestResponse<T> fromSuccessCode(SuccessCode successCode, T data) {
        RestResponse<T> response = new RestResponse<>();
        response.setStatus(successCode.getStatus());
        response.setMessage(successCode.getMessage());
        response.setData(data);
        return response;
    }
}