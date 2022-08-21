package io.coodle.easyshop.orderservice.statemachine;

import io.coodle.easyshop.orderservice.model.entity.Order;
import io.coodle.easyshop.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderStateMachineSagaOrchestratorImpl implements OrderStateMachineSagaOrchestrator {
    public static final String ORDER_ID_HEADER = "orderId";

    private final OrderRepository orderRepository;
    private final StateMachineFactory<OrderState, OrderEvent> stateMachineFactory;
    private final OrderStateMachineInterceptor orderStateMachineInterceptor;

    @Override
    @Transactional
    public Order create(Order order) {
        order.setId(null);
        order.setOrderStatus(OrderState.CREATED);

        Order createdOrder = orderRepository.save(order);

        validate(createdOrder);

        return createdOrder;
    }

    @Transactional
    @Override
    public void validate(Order order) {
        log.debug("Validating order with id: {}", order.getId());

        // Send event to statemachine
        sendEvent(order, OrderEvent.VALIDATE);
    }

    @Override
    public void processValidationSuccess(Order order) {
        log.debug("Validation success order with id: {}", order.getId());

        // Send event to state machine
        sendEvent(order, OrderEvent.VALIDATION_SUCCESS);
    }

    @Override
    public void processValidationFailed(Order order) {
        log.debug("Validation failed order with id: {}", order.getId());

        // Send event to state machine
        sendEvent(order, OrderEvent.VALIDATION_FAILED);
    }

    @Override
    public void allocate(Order order) {
        // Get order with state Validated
        Order validatedOrder = orderRepository.findById(order.getId()).get();

        sendEvent(validatedOrder, OrderEvent.ALLOCATE);
    }

    @Override
    public void processAllocationSuccess(Order order) {
        sendEvent(order, OrderEvent.ALLOCATION_SUCCESS);
    }

    @Override
    public void processAllocationFailed(Order order) {
        sendEvent(order, OrderEvent.ALLOCATION_FAILED);
    }

    @Override
    public void processAllocationNoInventory(Order order) {
        sendEvent(order, OrderEvent.ALLOCATION_NO_INVENTORY);
    }

    private void sendEvent(Order order, OrderEvent orderEvent){
        StateMachine<OrderState, OrderEvent> sm = build(order);

        Message msg = MessageBuilder.withPayload(orderEvent)
                                    .setHeader(ORDER_ID_HEADER, order.getId())
                                    .build();

        sm.sendEvent(msg);
    }

    private StateMachine<OrderState, OrderEvent> build(Order order){
        // Get the StateMachine with the specific id
        StateMachine<OrderState, OrderEvent> stateMachine = stateMachineFactory.getStateMachine(order.getId().toString());

        // Stop State Machine from running
        stateMachine.stop();

        // Override the state machine's state/event/transition
        // This is useful when you need to add new metadata for the
        // state machine before going to a certain state/event/transition.
        stateMachine.getStateMachineAccessor()
                .doWithAllRegions(stateMachineAccess -> {
                    stateMachineAccess.addStateMachineInterceptor(orderStateMachineInterceptor);

                    // This tells the state machine to force itself to go to a specific state
                    // instead of defaulting to it's initial state.
                    stateMachineAccess.resetStateMachine(
                        new DefaultStateMachineContext<>(order.getOrderStatus(),
                        null,
                        null,
                        null
                    ));
                });

        // Start the State Machine once again
        stateMachine.start();

        return stateMachine;
    }

}
