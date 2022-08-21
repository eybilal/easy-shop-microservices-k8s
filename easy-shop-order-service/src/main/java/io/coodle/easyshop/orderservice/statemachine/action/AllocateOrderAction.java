package io.coodle.easyshop.orderservice.statemachine.action;

import io.coodle.easyshop.orderservice.mapper.OrderMapper;
import io.coodle.easyshop.orderservice.messaging.OrderEventProducer;
import io.coodle.easyshop.orderservice.messaging.OrderTopics;
import io.coodle.easyshop.orderservice.model.event.AllocateOrderRequestEvent;
import io.coodle.easyshop.orderservice.model.dto.OrderDto;
import io.coodle.easyshop.orderservice.model.entity.Order;
import io.coodle.easyshop.orderservice.repository.OrderRepository;
import io.coodle.easyshop.orderservice.statemachine.OrderEvent;
import io.coodle.easyshop.orderservice.statemachine.OrderState;
import io.coodle.easyshop.orderservice.statemachine.OrderStateMachineSagaOrchestratorImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AllocateOrderAction implements Action<OrderState, OrderEvent> {
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderProducer;
    private final OrderMapper orderMapper;

    @Override
    public void execute(StateContext<OrderState, OrderEvent> stateContext) {
        Long orderId = (Long) stateContext.getMessage().getHeaders().get(OrderStateMachineSagaOrchestratorImpl.ORDER_ID_HEADER);
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            OrderDto orderDto = orderMapper.orderToOrderDto(orderOptional.get());

            orderProducer.send(
                    AllocateOrderRequestEvent.builder()
                            .orderDto(orderDto)
                            .build(),
                    OrderTopics.ALLOCATE_ORDER_REQUEST);

            log.debug("Order with id {} was sent to topic {}", orderId, OrderTopics.ALLOCATE_ORDER_REQUEST);
        } else {
            log.error(
                    "Order with id {} couldn't be send to topic {}, cause order not found",
                    orderId, OrderTopics.ALLOCATE_ORDER_REQUEST
            );
        }
    }
}
