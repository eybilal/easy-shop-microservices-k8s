package com.eybilal.orderservice.model.entity;

import com.eybilal.orderservice.model.pojo.BaseEntity;
import com.eybilal.orderservice.client.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class
OrderItem extends BaseEntity {
    // To avoid a cyclic reference between Order and OrderItem, due to the following facts:
    // 1- We are using the entity directly for the REST response (and NOT the DTO)
    // 2- We serialize to JSON directly the entity (and NOT the DTO)
    // 3- We have a bidirectional relation between Order and OrderItem
    // You should not serialize in JSON the Order entity when reading OrderItem
    // In my opinion, we don't need to serialize Order, because we already know
    // the entity OrderItem belongs to Order
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // Serialize Only for writing
    private Order order;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // Serialize Only for writing
    private Long productId;

    @Transient
    private Product product;    // Not persistent fot JPA

    @Column(name = "quantity")
    private int quantity;
}
