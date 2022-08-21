package io.coodle.easyshop.orderservice;

import io.coodle.easyshop.orderservice.model.entity.Order;
import io.coodle.easyshop.orderservice.repository.OrderRepository;
import io.coodle.easyshop.orderservice.statemachine.OrderState;
import io.coodle.easyshop.orderservice.statemachine.OrderStateMachineSagaOrchestrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderStateMachineSagaOrchestratorImplIT {
    @Autowired
    OrderStateMachineSagaOrchestrator orderStateMachineSagaOrchestrator;

    @Autowired
    OrderRepository orderRepository;

//    @Autowired
//     customerRepository;

//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Autowired
//    WireMockServer wireMockServer;
//
//    @Autowired
//    JmsTemplate jmsTemplate;

//    UUID beerId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
    }

    @Test
    void testNewToAllocated() {
        Order order = createOrder();

        Order createdOrder = orderStateMachineSagaOrchestrator.create(order);

        assertNotNull(createdOrder);
        assertEquals(OrderState.ALLOCATED, createdOrder.getOrderStatus());
    }


    private Order createOrder(){
        Order createdOrder = Order.builder()
                .orderNumber("2021-01-19/0000000001")
                .customerId(1L)
                .build();

//        Set<BeerOrderLine> lines = new HashSet<>();
//        lines.add(BeerOrderLine.builder()
//                .beerId(beerId)
//                .upc("12345")
//                .orderQuantity(1)
//                .beerOrder(beerOrder)
//                .build());

//        beerOrder.setBeerOrderLines(lines);

        return createdOrder;
    }
}
