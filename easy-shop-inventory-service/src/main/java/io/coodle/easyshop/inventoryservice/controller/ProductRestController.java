package io.coodle.easyshop.inventoryservice.controller;

import io.coodle.easyshop.common.domain.dto.ProductDto;
import io.coodle.easyshop.inventoryservice.domain.entity.Product;
import io.coodle.easyshop.inventoryservice.mapper.ProductMapper;
import io.coodle.easyshop.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(ProductRestController.BASE_PATH)
@Slf4j
public class ProductRestController {
    public static final String BASE_PATH = "/api/v1/products";

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<Collection<ProductDto>> findAllProducts(
        @RequestParam(value = "name", required = false) String name
    ) {
        List<ProductDto> products = productService.findAllProducts(name)
                                        .stream()
                                        .map(productMapper::productToProductDto)
                                        .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productMapper.productToProductDto(productService.findProductById(id)));
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
