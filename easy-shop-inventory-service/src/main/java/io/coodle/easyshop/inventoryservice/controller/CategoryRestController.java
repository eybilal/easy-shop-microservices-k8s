package io.coodle.easyshop.inventoryservice.controller;

import io.coodle.easyshop.inventoryservice.domain.entity.Category;
import io.coodle.easyshop.inventoryservice.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(CategoryRestController.BASE_PATH)
public class CategoryRestController {
    public static final String BASE_PATH = "/api/v1/categories";

    private final CategoryRestController categoryRestController;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Collection<Category> findAllCategories(
            @RequestParam(value = "name", required = false) String name
    ) {
        return categoryRestController.findAllCategories(name);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category findCategoryById(@PathVariable Long id) {
        return categoryRestController.findCategoryById(id);
    }

    @GetMapping(path = "/{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Product> findCategoryProductsById(@PathVariable Long id) {
        return categoryRestController.findCategoryProductsById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody @Validated final Category category) {
        return categoryRestController.createCategory(category);
    }

    @PutMapping(path = "/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody @Validated Category category) {
        return categoryRestController.updateCategory(id, category);
    }
}
