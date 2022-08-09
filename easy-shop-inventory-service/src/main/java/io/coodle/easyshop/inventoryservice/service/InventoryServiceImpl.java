package io.coodle.easyshop.inventoryservice.service;

import io.coodle.easyshop.inventoryservice.Exception.CategoryNotFoundException;
import io.coodle.easyshop.inventoryservice.Exception.ProductNotFoundException;
import io.coodle.easyshop.inventoryservice.entity.Category;
import io.coodle.easyshop.inventoryservice.entity.Product;
import io.coodle.easyshop.inventoryservice.repository.CategoryRepository;
import io.coodle.easyshop.inventoryservice.repository.ProductRepository;
import com.google.common.base.Strings;
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
public class InventoryServiceImpl implements InventoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

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

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Collection<Product> findAllProducts(String name) {
        if (!Strings.isNullOrEmpty(name)) {
            return productRepository.findByName(name);
        }

        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        BeanUtils.copyProperties(product, existingProduct, "id");

        return productRepository.save(existingProduct);
    }
}
