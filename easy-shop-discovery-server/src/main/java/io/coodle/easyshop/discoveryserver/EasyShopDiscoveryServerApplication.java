package io.coodle.easyshop.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EasyShopDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyShopDiscoveryServerApplication.class, args);
	}

}
