package io.coodle.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Collection;

@FeignClient(name = "inventory-service", url = "http://localhost:8080/api/v1")
public interface InventoryServiceClient {
    @GetMapping(value = "/products/{id}")
    Product findProductById(@PathVariable(name = "id") Long id);

    @GetMapping(value = "/products")
    Collection<Product> findAllProducts(@RequestHeader("Authorization") String authorization);
}
