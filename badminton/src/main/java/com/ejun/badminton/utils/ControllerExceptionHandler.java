package com.ejun.badminton.utils;

import com.ejun.badminton.utils.Exceptions.CustomException;
import com.ejun.badminton.utils.Exceptions.InvalidParameterException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Objects;


@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("handleHttpRequestMethodNotSupportedException", e);

        final ErrorResponse response
                = ErrorResponse
                .builder()
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .message(e.getMessage()).build();

        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        logger.error("handleDataIntegrityViolationException", e);

        final ErrorCode errorCode = ErrorCode.INVALID_PARAMETER;

        final ErrorResponse response
                = ErrorResponse
                .builder()
                .status(errorCode.getStatus())
                .message(e.getMostSpecificCause().getMessage()).build();

        return new ResponseEntity<>(response, Objects.requireNonNull(HttpStatus.resolve(errorCode.getStatus())));
    }
    //@Valid 검증 실패 시 Catch
    @ExceptionHandler(InvalidParameterException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException e) {
        logger.error("handleInvalidParameterException", e);

        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse response
                = ErrorResponse
                .builder()
                .status(errorCode.getStatus())
                .message(e.toString())
                .errors(e.getErrors())
                .build();

        return new ResponseEntity<>(response, Objects.requireNonNull(HttpStatus.resolve(errorCode.getStatus())));
    }

    //CustomException을 상속받은 클래스가 예외를 발생 시킬 시, Catch하여 ErrorResponse를 반환한다.
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        logger.error("handleAllException", e);

        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse response
                = ErrorResponse
                .builder()
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(e.toString())
                .build();

        return new ResponseEntity<>(response, Objects.requireNonNull(HttpStatus.resolve(errorCode.getStatus())));
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        logger.error("handleBadCredentialsException", e);

        ErrorCode errorcode = ErrorCode.BAD_CREDENTIALS;

        final ErrorResponse response
                = ErrorResponse
                .builder()
                .status(errorcode.getStatus())
                .code(errorcode.getCode())
                .message(e.getMessage()).build();

        return new ResponseEntity<>(response, Objects.requireNonNull(HttpStatus.resolve(errorcode.getStatus())));
    }

    @ExceptionHandler(SignatureException.class)
    protected ResponseEntity<ErrorResponse> handleSignatureException(SignatureException e) {
        logger.error("handleSignatureException", e);

        ErrorCode errorcode = ErrorCode.INVALID_JWT_SIGNATURE;

        final ErrorResponse response
                = ErrorResponse
                .builder()
                .status(errorcode.getStatus())
                .code(errorcode.getCode())
                .message(e.getMessage()).build();

        return new ResponseEntity<>(response, Objects.requireNonNull(HttpStatus.resolve(errorcode.getStatus())));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("handleJsonParseException", e);

        ErrorCode errorcode = ErrorCode.INVALID_JSON;

        final ErrorResponse response
                = ErrorResponse
                .builder()
                .status(errorcode.getStatus())
                .code(errorcode.getCode())
                .message(e.getMessage()).build();

        return new ResponseEntity<>(response, Objects.requireNonNull(HttpStatus.resolve(errorcode.getStatus())));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException e) {
        logger.error("handleExpiredJwtException", e);

        ErrorCode errorcode = ErrorCode.EXPIRED_JWT;

        final ErrorResponse response
                = ErrorResponse
                .builder()
                .status(errorcode.getStatus())
                .code(errorcode.getCode())
                .message(e.getMessage()).build();

        return new ResponseEntity<>(response, Objects.requireNonNull(HttpStatus.resolve(errorcode.getStatus())));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException e) {
        logger.error("handleNoResourceFoundException", e);

        ErrorCode errorCode = ErrorCode.ENDPOINT_NOT_FOUND;

        final ErrorResponse response
                = ErrorResponse
                .builder()
                .status(errorCode.getStatus())
                .message(e.getMessage()).build();

        return new ResponseEntity<>(response, Objects.requireNonNull(HttpStatus.resolve(errorCode.getStatus())));
    }

    //모든 예외를 핸들링하여 ErrorResponse 형식으로 반환한다.
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        logger.error("handleException", e);

        ErrorResponse response
                = ErrorResponse
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code(null)
                .message(e.toString())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
