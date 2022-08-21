package io.coodle.easyshop.orderservice.model.event;

import io.coodle.easyshop.orderservice.model.dto.OrderDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AllocateOrderRequestEvent {
    private OrderDto orderDto;
}
