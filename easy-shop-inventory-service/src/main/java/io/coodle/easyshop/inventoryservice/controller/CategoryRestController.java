package io.coodle.easyshop.inventoryservice.controller;

import io.coodle.easyshop.common.domain.dto.request.CategoryRequestDto;
import io.coodle.easyshop.common.domain.dto.response.CategoryResponseDto;
import io.coodle.easyshop.common.domain.dto.response.ProductResponseDto;
import io.coodle.easyshop.inventoryservice.domain.entity.Category;
import io.coodle.easyshop.inventoryservice.mapper.CategoryMapper;
import io.coodle.easyshop.inventoryservice.mapper.ProductMapper;
import io.coodle.easyshop.inventoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<List<CategoryResponseDto>> findAllCategories(
        @RequestParam(value = "name", required = false) String name
    ) {
        List<CategoryResponseDto> categories = categoryService.findAllCategories(name)
                                            .stream()
                                            .map(categoryMapper::categoryToCategoryResponseDto)
                                            .collect(Collectors.toList());

        return ResponseEntity.ok(categories);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryResponseDto> findCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryMapper.categoryToCategoryResponseDto(categoryService.findCategoryById(id)));
    }

    @GetMapping(path = "/{id}/products")
    public ResponseEntity<List<ProductResponseDto>> findCategoryProductsById(@PathVariable Long id) {
        List<ProductResponseDto> products = categoryService.findCategoryProductsById(id)
                                        .stream()
                                        .map(productMapper::productToProductResponseDto)
                                        .collect(Collectors.toList());

        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        Category createdCategory = categoryService.createCategory(categoryMapper.categoryRequestDtoToCategory(categoryRequestDto));

        return ResponseEntity
                .created(UriComponentsBuilder
                    .fromHttpUrl("https://www.coodle.io/" + BASE_PATH + createdCategory.getId().toString())
                    .build()
                    .toUri()
                )
                .build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryRequestDto categoryRequestDto) {
        categoryService.updateCategory(id, categoryMapper.categoryRequestDtoToCategory(categoryRequestDto));

        return ResponseEntity.noContent().build();
    }

    // TODO PartialUpdate
}
