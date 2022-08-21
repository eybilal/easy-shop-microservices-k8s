package io.coodle.easyshop.orderservice.statemachine.action;

import io.coodle.easyshop.orderservice.statemachine.OrderEvent;
import io.coodle.easyshop.orderservice.statemachine.OrderState;
import io.coodle.easyshop.orderservice.statemachine.OrderStateMachineSagaOrchestratorImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AllocationFailedAction implements Action<OrderState, OrderEvent> {
    @Override
    public void execute(StateContext<OrderState, OrderEvent> stateContext) {
        Long orderId = (Long) stateContext.getMessage().getHeaders().get(OrderStateMachineSagaOrchestratorImpl.ORDER_ID_HEADER);

        // Notify someone for this Failure, might be FrontendApp, customer service, etc

        log.error("Compensating Transaction...Allocation Failed for order with id {} ", orderId);
    }
}
