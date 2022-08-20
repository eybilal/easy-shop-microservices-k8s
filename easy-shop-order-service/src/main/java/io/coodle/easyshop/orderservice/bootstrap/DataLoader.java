
package io.coodle.easyshop.orderservice.bootstrap;

import io.coodle.easyshop.orderservice.client.*;
import io.coodle.easyshop.orderservice.model.entity.Order;
import io.coodle.easyshop.orderservice.model.entity.OrderItem;
import io.coodle.easyshop.orderservice.model.entity.OrderLog;
import io.coodle.easyshop.orderservice.service.OrderService;
import io.coodle.easyshop.orderservice.utils.JWTConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final OrderService orderService;
    private final CustomerServiceClient customerServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final AuthServiceClient authServiceClient;

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

//        inventoryServiceClient.findAllProducts(authorization).forEach(product -> {
//            OrderItem orderItem1 = OrderItem.builder()
//                                            .productId(product.getId())
//                                            .quantity(1)
//                                            .build();
//
//            createdOrder1.addOrderItem(orderItem1);
//            createdOrder1.addOrderLog(new OrderLog());

            orderService.placeOrder(createdOrder1);

//            OrderItem orderItem2 = OrderItem.builder()
//                                            .productId(product.getId())
//                                            .quantity(2)
//                                            .build();
//
//            createdOrder2.addOrderItem(orderItem2);
//            createdOrder2.addOrderLog(new OrderLog());

            orderService.placeOrder(createdOrder2);
//        });
    }
}
