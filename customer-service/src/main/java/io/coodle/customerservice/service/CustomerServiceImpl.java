package io.coodle.customerservice.service;

import io.coodle.customerservice.exception.CustomerNotFoundException;
import io.coodle.customerservice.model.entity.Customer;
import io.coodle.customerservice.repository.CustomerRepository;
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
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Customer> findAllCustomers(String name) {
        if (!Strings.isNullOrEmpty(name)) {
            return customerRepository.findByName(name);
        }

        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);

        BeanUtils.copyProperties(customer, existingCustomer, "id");

        return customerRepository.save(existingCustomer);
    }
}
