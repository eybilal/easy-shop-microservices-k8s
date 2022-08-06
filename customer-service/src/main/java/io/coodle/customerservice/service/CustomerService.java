package io.coodle.customerservice.service;

import io.coodle.customerservice.model.entity.Customer;

import java.util.Collection;

public interface CustomerService {
    Customer findCustomerById(Long id);
    Collection<Customer> findAllCustomers(String name);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);

}
