package io.coodle.easyshop.inventoryservice.web.controller;

import io.coodle.easyshop.inventoryservice.domain.entity.Category;
import io.coodle.easyshop.inventoryservice.domain.entity.Product;
import io.coodle.easyshop.inventoryservice.service.CategoryService;
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
@RequestMapping(CategoryRestController.BASE_PATH)
@Slf4j
public class CategoryRestController {
    public static final String BASE_PATH = "/api/v1/categories";

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Collection<Category>> findAllCategories(
        @RequestParam(value = "name", required = false) String name
    ) {
        return new ResponseEntity<>(categoryService.findAllCategories(name), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findCategoryById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/products")
    public ResponseEntity<Collection<Product>> findCategoryProductsById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findCategoryProductsById(id), HttpStatus.OK);
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
    public ResponseEntity updateCategory(@PathVariable Long id, @RequestBody @Validated Category category) {
        categoryService.updateCategory(id, category);

        return ResponseEntity.noContent().build();
    }

    // TODO PartialUpdate
}
