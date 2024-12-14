package com.study.mothr.web.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class RestControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {
        String parameterName = e.getParameterName();

        Map<String, String> errors = new HashMap<>();
        errors.put(parameterName, "Required " + parameterName);
        return errors;
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handlerMethodValidationException(HandlerMethodValidationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        String parameterName = e.getParameter().getParameterName();

        Map<String, String> errors = new HashMap<>();
        errors.put(parameterName, "Unknown Argument Type:" + parameterName);
        return errors;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<ExceptionResponse> handleException(WebRequest request, Model model,
                                                      HttpServletResponse response, Exception e) {
        return handle(request, model, ExceptionCode.INTERNAL_SERVER_ERROR, e);
    }

    private ResponseEntity<ExceptionResponse> handle(WebRequest request, Model model,
                                                     ExceptionCode exceptionCode, Exception e) {
        log.error("[EXCEPTION] {}\n from : {}", e.getMessage(), request, e);
        model.addAttribute("errorCode", exceptionCode);
        ExceptionResponse response = ExceptionResponse.of(exceptionCode, e);
        return new ResponseEntity<>(response, exceptionCode.getStatus());
    }

    private ResponseEntity<ExceptionResponse> handle(WebRequest request, Model model,
                                                     ExceptionCode exceptionCode, String message) {

        model.addAttribute("errorCode", exceptionCode);
        ExceptionResponse response = ExceptionResponse.of(exceptionCode, message);
        return new ResponseEntity<>(response, exceptionCode.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
