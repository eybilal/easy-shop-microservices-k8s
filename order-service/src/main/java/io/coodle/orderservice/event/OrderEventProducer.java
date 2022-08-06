package io.coodle.orderservice.event;

import io.coodle.orderservice.model.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;


/**
 * @author Bilal El Yousfi
 */
@Component
public class OrderEventProducer {
    public static final String ORDER_TOPIC = "orders";

    @Autowired
    private StreamBridge streamBridge;

    /**
     *
     * Publish the event to kafka broker with the topic orders
     *
     * @param order
     */
    public void publish(Order order) {
        System.out.println("********** Sent order to orders topic of Kafka **********");
        System.out.println(order.toString());
        streamBridge.send(ORDER_TOPIC, order);
    }
}
