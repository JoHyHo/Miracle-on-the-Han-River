package com.study.ddd.domain.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Email {
    private final String value;

    public Email(String value) {
        if (!value.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email email = (Email) o;
        return Objects.equals(getValue(), email.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
