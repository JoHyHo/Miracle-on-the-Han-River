package com.study.mothr.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse {

    public static <T> ResponseEntity<T> ok() {
        return ResponseEntity.ok().build();
    }

    public static <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<Void> noContent() {
        return ResponseEntity.noContent().build();
    }

    public static <T> ResponseEntity<T> badRequest(T body) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    public static <T> ResponseEntity<T> customResponse(HttpStatus status, T body) {
        return ResponseEntity.status(status).body(body);
    }

    public static <T> ResponseEntity<T> customResponse(HttpStatus status, T body, HttpHeaders headers) {
        return ResponseEntity.status(status).headers(headers).body(body);
    }
}
