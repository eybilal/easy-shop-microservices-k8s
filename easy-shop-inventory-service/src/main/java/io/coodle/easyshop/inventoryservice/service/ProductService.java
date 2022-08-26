package io.coodle.easyshop.inventoryservice.service;

import io.coodle.easyshop.inventoryservice.domain.entity.Product;

import java.util.Collection;

public interface ProductService {
    Product findProductById(Long id);
    Collection<Product> findAllProducts(String name);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
}
