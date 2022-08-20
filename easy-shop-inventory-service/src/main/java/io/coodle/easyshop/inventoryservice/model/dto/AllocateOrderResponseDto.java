package io.coodle.easyshop.inventoryservice.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AllocateOrderResponseDto {
    private Long orderId;
    private Boolean allocationError;
    private Boolean noInventory;
}
