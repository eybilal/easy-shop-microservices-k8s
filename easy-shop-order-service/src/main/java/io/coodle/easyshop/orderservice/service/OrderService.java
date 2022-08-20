package io.coodle.easyshop.orderservice.service;

import io.coodle.easyshop.orderservice.model.entity.Order;

public interface OrderService {
    Order placeOrder(Order order);
}
