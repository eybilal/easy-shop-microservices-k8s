package io.coodle.easyshop.orderservice.messaging;

import io.coodle.easyshop.orderservice.model.dto.AllocateOrderResponseDto;
import io.coodle.easyshop.orderservice.model.dto.ValidateOrderResponseDto;
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
public class OrderConsumer {
    private final OrderStateMachineSagaOrchestrator orderStateMachineOrchestrator;
    private final OrderRepository orderRepository;

    @Bean
    Consumer<ValidateOrderResponseDto> validateOrderResponseConsumer() {
        return (validateOrderResponseDto) -> {
            log.debug("Receiving {} from topic {}", validateOrderResponseDto, OrderTopics.VALIDATE_ORDER_RESPONSE);

            Optional<Order> orderOptional = orderRepository.findById(validateOrderResponseDto.getOrderId());

            if (orderOptional.isPresent()) {
                if (validateOrderResponseDto.getIsValid()) {
                    orderStateMachineOrchestrator.processValidationSuccess(orderOptional.get());

                    orderStateMachineOrchestrator.allocate(orderOptional.get());
                } else {
                    orderStateMachineOrchestrator.processValidationFailed(orderOptional.get());
                }
            } else {
                log.error("Order Not Found. Id: {}", validateOrderResponseDto.getOrderId());
            }
        };
    }

    @Bean
    Consumer<AllocateOrderResponseDto> allocateOrderResponseConsumer() {
        return (allocateOrderResponseDto) -> {
            log.debug("Receiving {} from topic {}", allocateOrderResponseDto, OrderTopics.ALLOCATE_ORDER_RESPONSE);

            Optional<Order> orderOptional = orderRepository.findById(allocateOrderResponseDto.getOrderId());

            if (orderOptional.isPresent()) {
                if(!allocateOrderResponseDto.getAllocationError() && !allocateOrderResponseDto.getNoInventory()) {
                    orderStateMachineOrchestrator.processAllocationSuccess(orderOptional.get());
                } else if(!allocateOrderResponseDto.getAllocationError() && allocateOrderResponseDto.getNoInventory()) {
                    orderStateMachineOrchestrator.processAllocationNoInventory(orderOptional.get());
                } else if(allocateOrderResponseDto.getAllocationError()) {
                    orderStateMachineOrchestrator.processAllocationFailed(orderOptional.get());
                }
            } else {
                log.error("Order Not Found. Id: {}", allocateOrderResponseDto.getOrderId());
            }
        };
    }
}
