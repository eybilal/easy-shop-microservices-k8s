package io.coodle.easyshop.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EasyShopAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyShopAuthServerApplication.class, args);
	}

	 /*
        Password Encoder
     */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
