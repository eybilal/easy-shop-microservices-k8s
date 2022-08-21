package io.coodle.easyshop.inventoryservice.model.event;

import io.coodle.easyshop.inventoryservice.model.dto.OrderDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ValidateOrderRequestEvent {
    private OrderDto orderDto;
}
