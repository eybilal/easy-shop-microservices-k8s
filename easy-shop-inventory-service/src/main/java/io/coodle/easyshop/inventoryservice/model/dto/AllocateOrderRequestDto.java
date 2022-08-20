package io.coodle.easyshop.inventoryservice.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AllocateOrderRequestDto {
    private OrderDto orderDto;
}
