package io.coodle.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "customer-service", url = "http://localhost:8080/api/v1/customers")
public interface CustomerServiceClient {
    @GetMapping(value = "/{id}")
    Customer findUserById(@RequestHeader("Authorization") String authorization, @PathVariable(name = "id") Long id);
}
