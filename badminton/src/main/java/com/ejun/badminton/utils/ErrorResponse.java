package com.ejun.badminton.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();

    private final String message; // Error Message

    private final String code; // Custom Error Code

    private final int status; // HTTP status code

    // fields that are not valid
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("errors")
    private List<CustomFieldError> customFieldErrors;

    public static class ErrorResponseBuilder{
            public ErrorResponseBuilder errors(Errors errors){
                List<CustomFieldError> _customFieldErrors = new ArrayList<>();

                List<FieldError> fieldErrors = errors.getFieldErrors();
                fieldErrors.forEach(error -> {
                    _customFieldErrors.add(new CustomFieldError(
                            Objects.requireNonNull(error.getCodes())[0],
                            error.getRejectedValue(),
                            error.getDefaultMessage()
                    ));
                });
                this.customFieldErrors = _customFieldErrors;
                return this;
            }
    }

        public record CustomFieldError(String field, Object value, String reason) {

    }
}
