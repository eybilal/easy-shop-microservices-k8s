package io.coodle.easyshop.inventoryservice.messaging;

import io.coodle.easyshop.inventoryservice.model.dto.AllocateOrderRequestDto;
import io.coodle.easyshop.inventoryservice.model.dto.AllocateOrderResponseDto;
import io.coodle.easyshop.inventoryservice.model.dto.ValidateOrderRequestDto;
import io.coodle.easyshop.inventoryservice.model.dto.ValidateOrderResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {
    private final OrderProducer orderProducer;

    @Bean
    Consumer<ValidateOrderRequestDto> validateOrderRequestConsumer() {
        return (validateOrderRequestDto) -> {
            log.debug("Receiving {} from topic {}", validateOrderRequestDto, OrderTopics.VALIDATE_ORDER_REQUEST);

            // Validate Order => check if exists, etc

            // Lock the Product for this order

            ValidateOrderResponseDto validateOrderResponseDto = ValidateOrderResponseDto.builder()
                    .orderId(validateOrderRequestDto.getOrderDto().getId())
                    .isValid(true)
                    .build();

            orderProducer.send(validateOrderResponseDto, OrderTopics.VALIDATE_ORDER_RESPONSE);
        };
    }

    @Bean
    Consumer<AllocateOrderRequestDto> allocateOrderRequestConsumer() {
        return (allocateOrderRequestDto) -> {
            log.debug("Receiving {} from topic {}", allocateOrderRequestDto, OrderTopics.ALLOCATE_ORDER_REQUEST);

            AllocateOrderResponseDto allocateOrderResponseDto = AllocateOrderResponseDto.builder()
                    .orderId(allocateOrderRequestDto.getOrderDto().getId())
                    .allocationError(false)
                    .noInventory(false)
                    .build();

            orderProducer.send(allocateOrderResponseDto, OrderTopics.ALLOCATE_ORDER_RESPONSE);
        };
    }
}
