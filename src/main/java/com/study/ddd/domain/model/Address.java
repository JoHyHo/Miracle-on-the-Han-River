package com.study.ddd.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Address {
    private final String street;
    private final String city;
    private final String state;
    private final String zipCode;

    @Builder
    public Address(String street, String city, String state, String zipCode) {
        if(street.isEmpty() || city.isEmpty() || state.isEmpty() || zipCode.isEmpty()) {
            throw new IllegalArgumentException("Address fields cannot be empty.");
        }
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getStreet(), address.getStreet()) &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getState(), address.getState()) &&
                Objects.equals(getZipCode(), address.getZipCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getCity(), getState(), getZipCode());
    }
}