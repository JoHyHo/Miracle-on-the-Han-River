package com.study.mothr.infrastructure.persistence.order;


import com.study.mothr.infrastructure.persistence.common.BaseEntityPersistence;
import com.study.mothr.infrastructure.persistence.user.AddressEmbeddable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity extends BaseEntityPersistence {

    @Column(nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatusEmbeddable orderStatus;

    @Embedded
    private PaymentInfoEmbeddable paymentInfo;

    @Embedded
    private AddressEmbeddable shippingAddress;

}
