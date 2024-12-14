package com.study.mothr.web.exception;

public class ApplicationException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public ApplicationException(ExceptionCode exceptionCode) {
        super();
        this.exceptionCode = exceptionCode;
    }

    public ApplicationException(ExceptionCode exceptionCode, String message) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public ApplicationException(ExceptionCode exceptionCode, String message, Throwable cause) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
    }

    public ApplicationException(ExceptionCode exceptionCode, Throwable cause) {
        super(cause);
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getErrorCode() {
        return exceptionCode;
    }

}