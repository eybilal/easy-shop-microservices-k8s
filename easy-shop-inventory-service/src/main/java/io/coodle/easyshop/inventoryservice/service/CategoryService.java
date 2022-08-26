package io.coodle.easyshop.inventoryservice.service;

import io.coodle.easyshop.inventoryservice.domain.entity.Category;
import io.coodle.easyshop.inventoryservice.domain.entity.Product;

import java.util.Collection;

public interface CategoryService {
    Category findCategoryById(Long id);
    Collection<Category> findAllCategories(String name);
    Collection<Product> findCategoryProductsById(Long id);

    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
}
