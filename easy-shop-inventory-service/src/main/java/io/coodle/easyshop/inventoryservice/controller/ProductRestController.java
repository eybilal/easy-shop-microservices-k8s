package io.coodle.easyshop.inventoryservice.controller;

import io.coodle.easyshop.common.domain.dto.request.ProductRequestDto;
import io.coodle.easyshop.common.domain.dto.response.ProductResponseDto;
import io.coodle.easyshop.inventoryservice.domain.entity.Category;
import io.coodle.easyshop.inventoryservice.domain.entity.Product;
import io.coodle.easyshop.inventoryservice.mapper.ProductMapper;
import io.coodle.easyshop.inventoryservice.service.CategoryService;
import io.coodle.easyshop.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
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
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<Collection<ProductResponseDto>> findAllProducts(
        @RequestParam(value = "name", required = false) String name
    ) {
        List<ProductResponseDto> products = productService.findAllProducts(name)
                                        .stream()
                                        .map(productMapper::productToProductResponseDto)
                                        .collect(Collectors.toList());

        return ResponseEntity.ok(products);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productMapper.productToProductResponseDto(productService.findProductById(id)));
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductRequestDto productRequestDto) {
        Category category = categoryService.findCategoryById(productRequestDto.getCategoryId());

        Product p = productMapper.productRequestDtoToProduct(productRequestDto);
        p.setCategory(category);

        Product createdProduct = productService.createProduct(p);

        return ResponseEntity
                .created(UriComponentsBuilder
                        .fromHttpUrl("https://www.coodle.io" + BASE_PATH + "/" + createdProduct.getId().toString())
                        .build()
                        .toUri()
                )
                .build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequestDto productRequestDto) {
        productService.updateProduct(id, productMapper.productRequestDtoToProduct(productRequestDto));

        return ResponseEntity.noContent().build();
    }

    // TODO PartialUpdate
}
