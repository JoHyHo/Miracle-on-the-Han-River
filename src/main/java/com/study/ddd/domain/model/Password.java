package com.study.ddd.domain.model;


import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class Password {
    private final String value;

    public Password(String value) {
        if (value == null || value.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Password)) return false;
        Password password = (Password) o;
        return Objects.equals(getValue(), password.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
