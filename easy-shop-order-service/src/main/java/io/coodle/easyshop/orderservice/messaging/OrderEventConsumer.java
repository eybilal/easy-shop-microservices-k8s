package io.coodle.easyshop.orderservice.messaging;

import io.coodle.easyshop.orderservice.model.event.AllocateOrderResponseEvent;
import io.coodle.easyshop.orderservice.model.event.ValidateOrderResponseEvent;
import io.coodle.easyshop.orderservice.model.entity.Order;
import io.coodle.easyshop.orderservice.repository.OrderRepository;
import io.coodle.easyshop.orderservice.statemachine.OrderStateMachineSagaOrchestrator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventConsumer {
    private final OrderStateMachineSagaOrchestrator orderStateMachineOrchestrator;
    private final OrderRepository orderRepository;

    @Bean
    Consumer<ValidateOrderResponseEvent> validateOrderResponseConsumer() {
        return (validateOrderResponseEvent) -> {
            log.debug("Receiving event {} from topic {}", validateOrderResponseEvent, OrderTopics.VALIDATE_ORDER_RESPONSE);

            Optional<Order> orderOptional = orderRepository.findById(validateOrderResponseEvent.getOrderId());

            if (orderOptional.isPresent()) {
                if (validateOrderResponseEvent.getIsValid()) {
                    orderStateMachineOrchestrator.processValidationSuccess(orderOptional.get());

                    orderStateMachineOrchestrator.allocate(orderOptional.get());
                } else {
                    orderStateMachineOrchestrator.processValidationFailed(orderOptional.get());
                }
            } else {
                log.error("Order Not Found. Id: {}", validateOrderResponseEvent.getOrderId());
            }
        };
    }

    @Bean
    Consumer<AllocateOrderResponseEvent> allocateOrderResponseConsumer() {
        return (allocateOrderResponseEvent) -> {
            log.debug("Receiving event {} from topic {}", allocateOrderResponseEvent, OrderTopics.ALLOCATE_ORDER_RESPONSE);

            Optional<Order> orderOptional = orderRepository.findById(allocateOrderResponseEvent.getOrderId());

            if (orderOptional.isPresent()) {
                if(!allocateOrderResponseEvent.getAllocationError() && !allocateOrderResponseEvent.getNoInventory()) {
                    orderStateMachineOrchestrator.processAllocationSuccess(orderOptional.get());
                } else if(!allocateOrderResponseEvent.getAllocationError() && allocateOrderResponseEvent.getNoInventory()) {
                    orderStateMachineOrchestrator.processAllocationNoInventory(orderOptional.get());
                } else if(allocateOrderResponseEvent.getAllocationError()) {
                    orderStateMachineOrchestrator.processAllocationFailed(orderOptional.get());
                }
            } else {
                log.error("Order Not Found. Id: {}", allocateOrderResponseEvent.getOrderId());
            }
        };
    }
}
