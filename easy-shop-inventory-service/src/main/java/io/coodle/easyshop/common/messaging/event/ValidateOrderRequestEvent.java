package io.coodle.easyshop.common.messaging.event;

import io.coodle.easyshop.common.domain.dto.OrderDto;
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
