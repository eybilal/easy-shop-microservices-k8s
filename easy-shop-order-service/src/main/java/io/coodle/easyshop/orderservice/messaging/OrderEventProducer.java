package io.coodle.easyshop.orderservice.messaging;

import io.coodle.easyshop.orderservice.model.event.AllocateOrderRequestEvent;
import io.coodle.easyshop.orderservice.model.event.ValidateOrderRequestEvent;
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
public class OrderEventProducer {
    private final StreamBridge streamBridge;

    public void send(ValidateOrderRequestEvent validateOrderRequestEvent, String topic) {
        log.debug("Sending event {} to topic {}", validateOrderRequestEvent, topic);

        streamBridge.send(topic, validateOrderRequestEvent);
    }

    public void send(AllocateOrderRequestEvent allocateOrderRequestEvent, String topic) {
        log.debug("Sending event {} to topic {}", allocateOrderRequestEvent, topic);

        streamBridge.send(topic, allocateOrderRequestEvent);
    }
}
