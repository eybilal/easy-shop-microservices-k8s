package io.coodle.easyshop.inventoryservice.messaging;

import io.coodle.easyshop.common.messaging.OrderTopics;
import io.coodle.easyshop.common.messaging.event.AllocateOrderRequestEvent;
import io.coodle.easyshop.common.messaging.event.AllocateOrderResponseEvent;
import io.coodle.easyshop.common.messaging.event.ValidateOrderRequestEvent;
import io.coodle.easyshop.common.messaging.event.ValidateOrderResponseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventConsumer {
    private final OrderEventProducer orderProducer;

    @Bean
    Consumer<ValidateOrderRequestEvent> validateOrderRequestConsumer() {
        return (validateOrderRequestEvent) -> {
            log.debug("Receiving event {} from topic {}", validateOrderRequestEvent, OrderTopics.VALIDATE_ORDER_REQUEST);

            // Validate Order => check if exists, etc

            // Lock the Product for this order

            ValidateOrderResponseEvent validateOrderResponseDto = ValidateOrderResponseEvent.builder()
                    .orderId(validateOrderRequestEvent.getOrderDto().getId())
                    .isValid(true)
                    .build();

            orderProducer.send(validateOrderResponseDto, OrderTopics.VALIDATE_ORDER_RESPONSE);
        };
    }

    @Bean
    Consumer<AllocateOrderRequestEvent> allocateOrderRequestConsumer() {
        return (allocateOrderRequestEvent) -> {
            log.debug("Receiving event {} from topic {}", allocateOrderRequestEvent, OrderTopics.ALLOCATE_ORDER_REQUEST);

            AllocateOrderResponseEvent allocateOrderResponseDto = AllocateOrderResponseEvent.builder()
                    .orderId(allocateOrderRequestEvent.getOrderDto().getId())
                    .allocationError(false)
                    .noInventory(false)
                    .build();

            orderProducer.send(allocateOrderResponseDto, OrderTopics.ALLOCATE_ORDER_RESPONSE);
        };
    }
}
