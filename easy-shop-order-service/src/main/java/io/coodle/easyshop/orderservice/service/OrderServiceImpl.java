package io.coodle.easyshop.orderservice.service;

import io.coodle.easyshop.orderservice.model.entity.Order;
import io.coodle.easyshop.orderservice.statemachine.OrderStateMachineSagaOrchestrator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderStateMachineSagaOrchestrator orderStateMachineOrchestrator;

    @Transactional
    @Override
    public Order placeOrder(Order order) {
        log.debug("Placing order {}", order);

        // TODO Why validating customer wih events not REST
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found. UUID: " + customerId));

        return orderStateMachineOrchestrator.create(order);
    }

    @Override
    public void pickupOrder(Long customerId, Long orderId) {

    }

    @Override
    public void cancelOrder(Long beerOrderId) {

    }
}
