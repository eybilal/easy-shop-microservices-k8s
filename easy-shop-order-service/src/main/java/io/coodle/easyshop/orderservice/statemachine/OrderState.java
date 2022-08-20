package io.coodle.easyshop.orderservice.statemachine;

public enum OrderState {
    CREATED,
    PENDING_VALIDATION, VALIDATED, VALIDATION_ERROR,
    PENDING_ALLOCATION, ALLOCATED, ALLOCATION_ERROR,
    PENDING_INVENTORY,
    PAID,
    PICKED_UP,
    DELIVERED, DELIVERY_ERROR,
    CANCELED
}

//public enum OrderStatus {
//    UNSHIPPED,
//    PENDING,
//    SHIPPED,
//    COMPLETED,
//    CANCELED,
//    REFUND_APPLIED
//}

// SUBMITTED
// DISPATCHED
