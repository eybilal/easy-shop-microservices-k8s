package io.coodle.easyshop.orderservice.service;

import io.coodle.easyshop.orderservice.model.entity.Order;
import io.coodle.easyshop.orderservice.statemachine.OrderState;
import io.coodle.easyshop.orderservice.repository.OrderRepository;
import io.coodle.easyshop.orderservice.statemachine.OrderStateMachineSagaOrchestrator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderStateMachineSagaOrchestrator orderStateMachineOrchestrator;

    @Override
    public Order placeOrder(Order order) {
        log.debug("Placing order {}", order);

        order.setId(null);
        order.setOrderStatus(OrderState.CREATED);

        Order createdOrder = orderRepository.save(order);

        orderStateMachineOrchestrator.validate(order);

        return createdOrder;
    }
}
