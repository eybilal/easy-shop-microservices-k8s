package com.eybilal.orderservice.model.entity;

import com.eybilal.orderservice.model.pojo.BaseEntity;
import com.eybilal.orderservice.model.pojo.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_log")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class OrderLog extends BaseEntity {
    private OrderStatus orderStatus;
    
    // To avoid a cyclic reference between Order and OrderLog, due to the following facts:
    // 1- We are using the entity directly for the REST response (and NOT the DTO)
    // 2- We serialize to JSON directly the entity (and NOT the DTO)
    // 3- We have a bidirectional relation between Order and OrderLog
    // You should not serialize in JSON the Order entity when reading OrderLog
    // In my opinion, we don't need to serialize Order, because we already know
    // the entity OrderLog belongs to Order
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // Serialize Only for writing
    private Order order;
}
