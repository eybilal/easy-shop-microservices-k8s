package io.coodle.easyshop.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    NewTopic validateOrderRequestTopic() {
        return TopicBuilder.name("validate_order_request")
                           .partitions(10)
                           .replicas(1)
                           .build();
    }

    @Bean
    NewTopic validateOrderResponseTopic() {
        return TopicBuilder.name("validate_order_response")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic allocateOrderRequestTopic() {
        return TopicBuilder.name("allocate_order_request")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic allocateOrderResponseTopic() {
        return TopicBuilder.name("allocate_order_response")
                .partitions(10)
                .replicas(1)
                .build();
    }
}
