package io.coodle.easyshop.inventoryservice.service;

import com.google.common.base.Strings;
import io.coodle.easyshop.inventoryservice.domain.entity.Category;
import io.coodle.easyshop.inventoryservice.domain.entity.Product;
import io.coodle.easyshop.inventoryservice.exception.CategoryNotFoundException;
import io.coodle.easyshop.inventoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
/**
 * Methods are Transactional, means that JPA run commit after calling
 * the method
 */
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    @Override
    public Collection<Category> findAllCategories(String name) {
        if (!Strings.isNullOrEmpty(name)) {
            return categoryRepository.findByName(name);
        }

        return categoryRepository.findAll();
    }

    @Override
    public Collection<Product> findCategoryProductsById(Long id) {
        return findCategoryById(id).getProducts();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);

        BeanUtils.copyProperties(category, existingCategory, "id");

        return categoryRepository.save(existingCategory);
    }
}
