package io.coodle.easyshop.inventoryservice.model.event;

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
