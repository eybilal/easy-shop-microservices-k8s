package io.coodle.easyshop.orderservice.statemachine;

import io.coodle.easyshop.orderservice.model.entity.Order;

public interface OrderStateMachineSagaOrchestrator {
    Order create(Order order);
    void validate(Order order);
    void processValidationSuccess(Order order);
    void processValidationFailed(Order order);

    void allocate(Order order);
    void processAllocationSuccess(Order order);
    void processAllocationFailed(Order order);
    void processAllocationNoInventory(Order order);

    // void pay();
    // void processPaymentSuccess();
    // void processPaymentFailed();

    // void cancel();
}
