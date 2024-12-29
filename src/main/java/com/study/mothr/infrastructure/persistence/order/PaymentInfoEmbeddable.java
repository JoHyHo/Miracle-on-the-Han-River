package com.study.mothr.infrastructure.persistence.order;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfoEmbeddable {
    private String paymentMethod;
    private String transactionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        PaymentInfoEmbeddable that = (PaymentInfoEmbeddable) o;
        return Objects.equals(paymentMethod, that.paymentMethod) &&
               Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentMethod, transactionId);
    }
}
