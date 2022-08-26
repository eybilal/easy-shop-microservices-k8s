package io.coodle.easyshop.inventoryservice.web.controller;

import io.coodle.easyshop.inventoryservice.domain.entity.Product;
import io.coodle.easyshop.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(ProductRestController.BASE_PATH)
@Slf4j
public class ProductRestController {
    public static final String BASE_PATH = "/api/v1/products";

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Collection<Product>> findAllProducts(
        @RequestParam(value = "name", required = false) String name
    ) {
        return new ResponseEntity<>(productService.findAllProducts(name), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Validated Product product) {
        Product createdProduct = productService.createProduct(product);

        return ResponseEntity
                .created(UriComponentsBuilder
                        .fromHttpUrl(BASE_PATH + createdProduct.getId().toString())
                        .build()
                        .toUri()
                )
                .build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody @Validated Product product) {
        productService.updateProduct(id, product);

        return ResponseEntity.noContent().build();
    }

    // TODO PartialUpdate
}
