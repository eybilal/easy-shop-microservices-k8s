package io.coodle.easyshop.orderservice.model.event;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AllocateOrderResponseEvent {
    private Long orderId;
    private Boolean allocationError = false;
    private Boolean noInventory = false;
}
