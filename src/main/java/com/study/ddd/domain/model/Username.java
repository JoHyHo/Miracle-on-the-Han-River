package com.study.ddd.domain.model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Username {
    private final String value;

    public Username(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        this.value = value;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Username)) return false;
        Username username = (Username) o;
        return Objects.equals(getValue(), username.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
