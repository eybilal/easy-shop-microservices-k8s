package io.coodle.easyshop.inventoryservice.controller;

import io.coodle.easyshop.inventoryservice.domain.entity.Product;
import io.coodle.easyshop.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(ProductRestController.BASE_PATH)
public class ProductRestController {
    public static final String BASE_PATH = "/api/v1/products";

    private final ProductService productService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Collection<Product> findAllProducts(
            @RequestParam(required = false) String name
    ) {
        return productService.findAllProducts(name);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product findProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Validated final Product product) {
        return productService.createProduct(product);
    }

    @PutMapping(path = "/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody @Validated Product product) {
        return productService.updateProduct(id, product);
    }
}
