package io.coodle.easyshop.orderservice.model.entity;

import io.coodle.easyshop.orderservice.client.Customer;
import io.coodle.easyshop.orderservice.model.pojo.OrderStatus;
import io.coodle.easyshop.orderservice.model.pojo.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "`order`")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Order extends BaseEntity {
    private String orderNumber;

    private OrderStatus orderStatus;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // Serialize Only for writing
    private Long customerId;

    @Transient
    private Customer customer;  // Not persistent for JPA

    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Collection<OrderItem> orderItems;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Collection<OrderLog> orderLogs;

    public void addOrderItem(OrderItem orderItem) {
        if(this.orderItems == null) {
            this.orderItems = new HashSet<>();
        }

        this.orderItems.add(orderItem);
    }

    public void addOrderLog(OrderLog orderLog) {
        if(this.orderLogs == null) {
            this.orderLogs = new HashSet<>();
        }

        this.orderLogs.add(orderLog);
    }
}
