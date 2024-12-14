package com.study.mothr.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()),
    NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.getReasonPhrase()),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.getReasonPhrase()),
    REQUEST_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, HttpStatus.REQUEST_TIMEOUT.name(), HttpStatus.REQUEST_TIMEOUT.getReasonPhrase()),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.name(), HttpStatus.UNAUTHORIZED.getReasonPhrase()),
    FORBIDDEN(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.name(), HttpStatus.FORBIDDEN.getReasonPhrase()),
    NO_CONTENT(HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.name(), HttpStatus.NO_CONTENT.getReasonPhrase()),
    CONFLICT(HttpStatus.CONFLICT, HttpStatus.CONFLICT.name(), HttpStatus.CONFLICT.getReasonPhrase())
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
