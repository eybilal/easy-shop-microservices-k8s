package io.coodle.easyshop.orderservice.messaging;

import io.coodle.easyshop.orderservice.model.dto.AllocateOrderRequestDto;
import io.coodle.easyshop.orderservice.model.dto.ValidateOrderRequestDto;
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
     * @param validateOrderRequestDto
     * @param topic
     */
    public void send(ValidateOrderRequestDto validateOrderRequestDto, String topic) {
        log.debug("Sending validateOrderRequest {} to topic {}", validateOrderRequestDto, topic);

        streamBridge.send(topic, validateOrderRequestDto);
    }

    public void send(AllocateOrderRequestDto allocateOrderRequestDto, String topic) {
        log.debug("Sending allocateOrderRequestDto {} to topic {}", allocateOrderRequestDto, topic);

        streamBridge.send(topic, allocateOrderRequestDto);
    }
}
