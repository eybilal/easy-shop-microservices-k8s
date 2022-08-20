package io.coodle.easyshop.orderservice.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AllocateOrderResponseDto {
    private Long orderId;
    private Boolean allocationError = false;
    private Boolean noInventory = false;
}
