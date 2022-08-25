package io.coodle.easyshop.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
    name = "customer-service",
    url = "http://localhost:8080/api/v1/customers",
    configuration = FeignClientConfig.class
)
public interface CustomerServiceClient {
    @GetMapping(value = "/{id}")
    Customer findUserById(@PathVariable(name = "id") Long id);
}
