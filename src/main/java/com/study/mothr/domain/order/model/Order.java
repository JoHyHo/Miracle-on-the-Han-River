package com.study.mothr.domain.order.model;


import com.study.mothr.domain.common.BaseEntity;
import com.study.mothr.domain.user.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {
    private Long userId;
    private List<OrderItem> orderItems;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private PaymentInfo paymentInfo;
    private Address shippingAddress;


    private BigDecimal calculateTotalAmount() {
        return orderItems.stream()
                         .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                         .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void pay(PaymentInfo paymentInfo) {
        if (this.orderStatus != OrderStatus.CREATED) {
            throw new IllegalStateException("결제할 수 없는 상태입니다.");
        }
        this.paymentInfo = paymentInfo;
        this.orderStatus = OrderStatus.PAID;
        updateTimestamp();
    }

    public void ship() {
        if (this.orderStatus != OrderStatus.PAID) {
            throw new IllegalStateException("배송할 수 없는 상태입니다.");
        }
        this.orderStatus = OrderStatus.SHIPPED;
        updateTimestamp();
    }

    public void complete() {
        if (this.orderStatus != OrderStatus.SHIPPED) {
            throw new IllegalStateException("완료할 수 없는 상태입니다.");
        }
        this.orderStatus = OrderStatus.COMPLETED;
        updateTimestamp();
    }

    public void cancel() {
        if (this.orderStatus == OrderStatus.COMPLETED || this.orderStatus == OrderStatus.CANCELLED) {
            throw new IllegalStateException("취소할 수 없는 상태입니다.");
        }
        this.orderStatus = OrderStatus.CANCELLED;
        updateTimestamp();
    }

}
