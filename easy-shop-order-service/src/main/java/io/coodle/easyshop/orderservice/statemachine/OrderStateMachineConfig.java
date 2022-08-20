package io.coodle.easyshop.orderservice.statemachine;

import io.coodle.easyshop.orderservice.statemachine.action.AllocateOrderAction;
import io.coodle.easyshop.orderservice.statemachine.action.ValidateOrderAction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * Config when placing a new order
 */
@Configuration
@EnableStateMachineFactory
@RequiredArgsConstructor
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {
    private final ValidateOrderAction validateOrderAction;
    private final AllocateOrderAction allocateOrderAction;

    // Define States when placing an order
    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderState.CREATED)
                .states(EnumSet.allOf(OrderState.class))
                .end(OrderState.PICKED_UP)
                .end(OrderState.DELIVERED)
                .end(OrderState.CANCELED)
                .end(OrderState.DELIVERY_ERROR)
                .end(OrderState.VALIDATION_ERROR)
                .end(OrderState.ALLOCATION_ERROR);

    }

    // Define State Transition when placing an order
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions.withExternal()
                    .source(OrderState.CREATED)
                    .target(OrderState.PENDING_VALIDATION)
                    .event(OrderEvent.VALIDATE)
                    .action(validateOrderAction)
                .and().withExternal()
                    .source(OrderState.PENDING_VALIDATION)
                    .target(OrderState.VALIDATED)
                    .event(OrderEvent.VALIDATION_PASSED)
                .and().withExternal()
                    .source(OrderState.PENDING_VALIDATION)
                    .target(OrderState.CANCELED)
                    .event(OrderEvent.CANCEL)
                .and().withExternal()
                    .source(OrderState.PENDING_VALIDATION)
                    .target(OrderState.VALIDATION_ERROR)
                    .event(OrderEvent.VALIDATION_FAILED)
                    //.action(validationFailureAction)
                .and().withExternal()
                    .source(OrderState.VALIDATED)
                    .target(OrderState.PENDING_ALLOCATION)
                    .event(OrderEvent.ALLOCATE)
                    .action(allocateOrderAction)
                .and().withExternal()
                    .source(OrderState.VALIDATED)
                    .target(OrderState.CANCELED)
                    .event(OrderEvent.CANCEL)
                .and().withExternal()
                    .source(OrderState.PENDING_ALLOCATION)
                    .target(OrderState.ALLOCATED)
                    .event(OrderEvent.ALLOCATION_SUCCESS)
                .and().withExternal()
                    .source(OrderState.PENDING_ALLOCATION)
                    .target(OrderState.ALLOCATION_ERROR)
                    .event(OrderEvent.ALLOCATION_FAILED)
                    //.action(allocationFailureAction)
                .and().withExternal()
                    .source(OrderState.PENDING_ALLOCATION)
                    .target(OrderState.CANCELED)
                    .event(OrderEvent.CANCEL)
                .and().withExternal()
                    .source(OrderState.PENDING_ALLOCATION)
                    .target(OrderState.PENDING_INVENTORY)
                    .event(OrderEvent.ALLOCATION_NO_INVENTORY)
                .and().withExternal()
                    .source(OrderState.ALLOCATED)
                    .target(OrderState.PICKED_UP)
                    .event(OrderEvent.PICK_UP)
                .and().withExternal()
                    .source(OrderState.ALLOCATED)
                    .target(OrderState.CANCELED)
                    .event(OrderEvent.CANCEL);
                    //.action(deallocateOrderAction);
    }
}
