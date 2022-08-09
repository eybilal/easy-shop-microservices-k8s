package io.coodle.easyshop.orderservice.web.rest.v1;

import io.coodle.easyshop.orderservice.repository.OrderItemRepository;
import io.coodle.easyshop.orderservice.repository.OrderRepository;
import io.coodle.easyshop.orderservice.client.InventoryServiceClient;
import io.coodle.easyshop.orderservice.client.CustomerServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(OrderRestController.BASE_URL)
@RequiredArgsConstructor
public class OrderRestController {
    public static final String BASE_URL = "/api/v1/orders";

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerServiceClient customerServiceClient;
    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    private StreamBridge streamBridge;

//    @GetMapping(value = "/details/{id}")
//    Order findOrderDetailsById(@PathVariable(name = "id") Long id) {
//        Order order = orderRepository.findById(id).get();
//        order.setCustomer(customerServiceClient.findUserById(order.getCustomerId()));
//        order.getOrderItems().forEach(orderItem -> {
//            orderItem.setProduct(inventoryServiceClient.findProductById(orderItem.getProductId()));
//        });
//
//        return order;
//    }
}
