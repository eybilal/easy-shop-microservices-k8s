package io.coodle.inventoryservice.controller;

import io.coodle.inventoryservice.entity.Category;
import io.coodle.inventoryservice.entity.Product;
import io.coodle.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(InventoryRestController.BASE_PATH)
public class InventoryRestController {
    public static final String BASE_PATH = "/api/v1";

    private final InventoryService inventoryService;

    /***************************************** Categories *****************************************/
    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Category> findAllCategories(
            @RequestParam(required = false) String name
    ) {
        return inventoryService.findAllCategories(name);
    }

    @GetMapping(path = "/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category findCategoryById(@PathVariable Long id) {
        return inventoryService.findCategoryById(id);
    }

    @GetMapping(path = "/categories/{id}/products")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Product> findCategoryProductsById(@PathVariable Long id) {
        return inventoryService.findCategoryProductsById(id);
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody @Validated final Category category) {
        return inventoryService.createCategory(category);
    }

    @PutMapping(path = "/categories/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody @Validated Category category) {
        return inventoryService.updateCategory(id, category);
    }


    /***************************************** Products *****************************************/
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Product> findAllProducts(
            @RequestParam(required = false) String name
    ) {
        return inventoryService.findAllProducts(name);
    }

    @GetMapping(path = "/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product findProductById(@PathVariable Long id) {
        return inventoryService.findProductById(id);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody @Validated final Product product) {
        return inventoryService.createProduct(product);
    }

    @PutMapping(path = "/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody @Validated Product product) {
        return inventoryService.updateProduct(id, product);
    }
}
