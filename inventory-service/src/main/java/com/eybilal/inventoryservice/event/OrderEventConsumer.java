package com.eybilal.inventoryservice.event;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class OrderEventConsumer {

    @Bean
    Consumer<Order> orderConsumer() {
        return (payload) -> {
            System.out.println("********** Received from orders topic from Kafka **********");
            System.out.println(payload.toString());
        };
    }
}
