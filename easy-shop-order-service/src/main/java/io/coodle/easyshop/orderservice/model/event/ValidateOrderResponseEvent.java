package io.coodle.easyshop.orderservice.model.event;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ValidateOrderResponseEvent {
    private Long orderId;
    private Boolean isValid;
}
