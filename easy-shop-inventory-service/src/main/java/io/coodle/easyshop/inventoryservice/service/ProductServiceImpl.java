package io.coodle.easyshop.inventoryservice.service;

import com.google.common.base.Strings;
import io.coodle.easyshop.inventoryservice.domain.entity.Product;
import io.coodle.easyshop.inventoryservice.exception.ProductNotFoundException;
import io.coodle.easyshop.inventoryservice.repository.ProductRepository;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
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
