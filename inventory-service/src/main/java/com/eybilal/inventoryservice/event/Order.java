package com.eybilal.inventoryservice.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order {
    private String orderNumber;

    //private OrderStatus orderStatus;


    private Long customerId;


    //private Collection<OrderItem> orderItems;
    //private Collection<OrderLog> orderLogs;
}
