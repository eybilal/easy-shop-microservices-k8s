package io.coodle.easyshop.inventoryservice;

import io.coodle.easyshop.common.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.binder.kafka.streams.endpoint.KafkaStreamsTopologyEndpointAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

// Bug: Unable to map duplicate endpoint operations:
// [MBean call 'kafkaStreamsTopology'] to topologyEndpoint
// As a workaround I excluded KafkaStreamsTopologyEndpointAutoConfiguration.class
// in @SpringBootApplication annotation
@SpringBootApplication(exclude= {KafkaStreamsTopologyEndpointAutoConfiguration.class})

// By default, All the classes and sub-packages will be scanned automatically
// under Spring Boot main class package.
// During the scan, it will detect @Component, @Configurations, @Bean annotated classes,
// and methods.
// The package common is outside the spring boot main class. It will not be scanned automatically
//
// Solution 1
// ----------
// Override scanned packages of @SpringBootApplication annotation
// @ComponentScan({
// 	 "io.coodle.easyshop.common",
//	 "io.coodle.easyshop.inventoryservice"
// })
//
// Solution 2
// ----------
// Since we only need one class to be scanned on start up, we will use @Import annotation
@Import(SecurityConfig.class)

/**
 * @EnableDiscoveryClient vs @EnableEurekaClient ?
 *
 * There are multiple implementations of "Discovery Service" (eureka, consul, zookeeper).
 *
 * @EnableDiscoveryClient lives in spring-cloud-commons and picks the implementation on the classpath.
 *
 * @EnableEurekaClient lives in spring-cloud-netflix and only works for eureka.
 *
 * If eureka is on your classpath, they are effectively the same.
 */
@EnableDiscoveryClient
public class EasyShopInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyShopInventoryServiceApplication.class, args);
	}

}
