package io.coodle.easyshop.inventoryservice.messaging;

import io.coodle.easyshop.inventoryservice.model.dto.AllocateOrderResponseDto;
import io.coodle.easyshop.inventoryservice.model.dto.ValidateOrderResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;


/**
 * @author Bilal El Yousfi
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {
    private final StreamBridge streamBridge;

    /**
     *
     * Send message to kafka broker with a specific topic
     *
     * @param validateOrderResponseDto
     * @param topic
     */
    public void send(ValidateOrderResponseDto validateOrderResponseDto, String topic) {
        log.debug("Sending {} to topic {}", validateOrderResponseDto, topic);

        streamBridge.send(topic, validateOrderResponseDto);
    }

    public void send(AllocateOrderResponseDto allocateOrderResponseDto, String topic) {
        log.debug("Sending {} to topic {}", allocateOrderResponseDto, topic);

        streamBridge.send(topic, allocateOrderResponseDto);
    }
}
