package io.coodle.easyshop.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "auth-service",
    url = "${easy-shop-clients.auth.url}"
)
public interface AuthServiceClient {
    @PostMapping(value = "/login")
    IdToken login(@RequestBody UsernamePassword usernamePassword);
}
