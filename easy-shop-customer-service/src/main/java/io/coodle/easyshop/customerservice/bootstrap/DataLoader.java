package io.coodle.easyshop.customerservice.bootstrap;

import io.coodle.easyshop.customerservice.model.entity.Customer;
import io.coodle.easyshop.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        initCustomers();
    }

    private void initCustomers() {
        Customer customer1 = customerRepository.save(
                Customer.builder()
                        .name("Bilal")
                        .email("bilal@gmail.com")
                        .build()
        );

        Customer customer2 = customerRepository.save(
                Customer.builder()
                        .name("Najlae")
                        .email("najlae@gmail.com")
                        .build()
        );
    }
}
