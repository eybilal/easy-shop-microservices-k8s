package io.coodle.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
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
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	 /*
        Password Encoder
     */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
