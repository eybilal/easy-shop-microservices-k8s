
package com.eybilal.orderservice.bootstrap;

import com.eybilal.orderservice.client.*;
import com.eybilal.orderservice.event.OrderEventProducer;
import com.eybilal.orderservice.model.entity.Order;
import com.eybilal.orderservice.model.entity.OrderItem;
import com.eybilal.orderservice.model.entity.OrderLog;
import com.eybilal.orderservice.repository.OrderItemRepository;
import com.eybilal.orderservice.repository.OrderRepository;
import com.eybilal.orderservice.utils.JWTConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerServiceClient customerServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final AuthServiceClient authServiceClient;
    private final OrderEventProducer orderEventListener;

    @Override
    public void run(String... args) throws Exception {
        UsernamePassword usernamePassword = UsernamePassword.builder()
                .username("user1")
                .password("user1.")
                .build();

        final IdToken idToken = authServiceClient.login(usernamePassword);
        final String authorization = JWTConstants.TOKEN_PREFIX + idToken.getAccessToken();

        final Customer customer1 = customerServiceClient.findUserById(
                authorization,
                1L
        );

        Order createdOrder1 = Order.builder()
                .orderNumber("2021-01-19/0000000001")
                .customerId(customer1.getId())
                .build();

        Order createdOrder2 = Order.builder()
                .orderNumber("2021-01-19/0000000002")
                .customerId(customer1.getId())
                .build();

        inventoryServiceClient.findAllProducts(authorization).forEach(product -> {
            OrderItem orderItem1 = OrderItem.builder()
                                            .productId(product.getId())
                                            .quantity(1)
                                            .build();

            createdOrder1.addOrderItem(orderItem1);
            createdOrder1.addOrderLog(new OrderLog());

            orderRepository.save(createdOrder1);
            orderEventListener.publish(createdOrder1);

            OrderItem orderItem2 = OrderItem.builder()
                                            .productId(product.getId())
                                            .quantity(2)
                                            .build();

            createdOrder2.addOrderItem(orderItem2);
            createdOrder2.addOrderLog(new OrderLog());

            orderRepository.save(createdOrder2);
            orderEventListener.publish(createdOrder2);
        });
    }
}
