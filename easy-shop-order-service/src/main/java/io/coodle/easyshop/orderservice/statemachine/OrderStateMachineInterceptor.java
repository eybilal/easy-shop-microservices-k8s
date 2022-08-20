package io.coodle.easyshop.orderservice.statemachine;

import io.coodle.easyshop.orderservice.model.entity.Order;
import io.coodle.easyshop.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderStateMachineInterceptor extends StateMachineInterceptorAdapter<OrderState, OrderEvent> {
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public void preStateChange(State<OrderState, OrderEvent> state, Message<OrderEvent> message, Transition<OrderState, OrderEvent> transition, StateMachine<OrderState, OrderEvent> stateMachine) {
        // If message is not null
        Optional.ofNullable(message).ifPresent(msg -> {
            // If orderId is not null
            Optional.ofNullable((Long) msg.getHeaders().getOrDefault(OrderStateMachineSagaOrchestratorImpl.ORDER_ID_HEADER, null))
                    .ifPresent(orderId -> {
                        log.debug("Saving state for order id: {} and status: {}", orderId, state.getId());

                        Order order = orderRepository.getOne(orderId);
                        order.setOrderStatus(state.getId());

                        // Default behaviour of Hibernate is LAZY
                        // Force Hibernate to go to database and save.
                        orderRepository.saveAndFlush(order);
                    });
        });
    }
}
