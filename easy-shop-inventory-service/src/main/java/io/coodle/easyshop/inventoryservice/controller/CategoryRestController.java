package io.coodle.easyshop.inventoryservice.controller;

import io.coodle.easyshop.common.domain.dto.CategoryDto;
import io.coodle.easyshop.common.domain.dto.ProductDto;
import io.coodle.easyshop.inventoryservice.domain.entity.Category;
import io.coodle.easyshop.inventoryservice.mapper.CategoryMapper;
import io.coodle.easyshop.inventoryservice.mapper.ProductMapper;
import io.coodle.easyshop.inventoryservice.service.CategoryService;
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
@RequestMapping(CategoryRestController.BASE_PATH)
@Slf4j
public class CategoryRestController {
    public static final String BASE_PATH = "/api/v1/categories";

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAllCategories(
        @RequestParam(value = "name", required = false) String name
    ) {
        List<CategoryDto> categories = categoryService.findAllCategories(name)
                                            .stream()
                                            .map(categoryMapper::categoryToCategoryDto)
                                            .collect(Collectors.toList());

        return ResponseEntity.ok(categories);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryMapper.categoryToCategoryDto(categoryService.findCategoryById(id)));
    }

    @GetMapping(path = "/{id}/products")
    public ResponseEntity<Collection<ProductDto>> findCategoryProductsById(@PathVariable Long id) {
        List<ProductDto> products = categoryService.findCategoryProductsById(id)
                                        .stream()
                                        .map(productMapper::productToProductDto)
                                        .collect(Collectors.toList());

        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody @Validated Category category) {
        Category createdCategory = categoryService.createCategory(category);

        return ResponseEntity
                .created(UriComponentsBuilder
                    .fromHttpUrl("https://www.coodle.io/" + BASE_PATH + createdCategory.getId().toString())
                    .build()
                    .toUri()
                )
                .build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id, @RequestBody @Validated Category category) {
        categoryService.updateCategory(id, category);

        return ResponseEntity.noContent().build();
    }

    // TODO PartialUpdate
}
