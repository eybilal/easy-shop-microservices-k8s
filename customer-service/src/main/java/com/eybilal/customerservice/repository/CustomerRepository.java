package com.eybilal.customerservice.repository;

import com.eybilal.customerservice.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Collection<Customer> findByName(String name);
}
