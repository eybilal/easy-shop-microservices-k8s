package com.eybilal.inventoryservice.repository;

import com.eybilal.inventoryservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Collection<Category> findByName(String name);
}
