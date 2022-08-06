package com.eybilal.customerservice.controller;

import com.eybilal.customerservice.model.entity.Customer;
import com.eybilal.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(CustomerRestController.BASE_PATH)
public class CustomerRestController {
    public static final String BASE_PATH = "/api/v1/customers";

    private final CustomerService customerService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Collection<Customer> findAllUsers(
        @RequestParam(required = false) String name
    ) {
        return customerService.findAllCustomers(name);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer findUserByName(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody @Validated final Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping(path = "/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody @Validated Customer customer) {
        return customerService.updateCustomer(id, customer);
    }
}
