package io.coodle.easyshop.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.binder.kafka.streams.endpoint.KafkaStreamsTopologyEndpointAutoConfiguration;

/**
 * @author Bilal El Yousfi
 */
// Bug: Unable to map duplicate endpoint operations:
// [MBean call 'kafkaStreamsTopology'] to topologyEndpoint
// As a workaround I excluded KafkaStreamsTopologyEndpointAutoConfiguration.class
// in @SpringBootApplication annotation
@SpringBootApplication(exclude= {KafkaStreamsTopologyEndpointAutoConfiguration.class})
@EnableFeignClients
public class EasyShopOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyShopOrderServiceApplication.class, args);
	}
}
