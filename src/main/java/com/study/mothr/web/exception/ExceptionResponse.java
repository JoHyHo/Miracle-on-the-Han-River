package com.study.mothr.web.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {

    private String message;
    private String code;
    private HttpStatus status;
    private String detail;

    ExceptionResponse(ExceptionCode code) {
        this(code, null);
    }

    ExceptionResponse(ExceptionCode code, String detail) {
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.code = code.getCode();
        this.detail = detail;
    }

    public static ExceptionResponse of(ExceptionCode code) {
        return new ExceptionResponse(code);
    }

    public static ExceptionResponse of(ExceptionCode code, Exception e) {
        return new ExceptionResponse(code, e.getMessage());
    }

    public static ExceptionResponse of(ExceptionCode code, String message) {
        return new ExceptionResponse(code, message);
    }
}