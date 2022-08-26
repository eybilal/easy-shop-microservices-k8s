package io.coodle.easyshop.common.messaging.event;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AllocateOrderResponseEvent {
    private Long orderId;
    private Boolean allocationError;
    private Boolean noInventory;
}
