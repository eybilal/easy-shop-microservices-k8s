package io.coodle.easyshop.inventoryservice.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ValidateOrderRequestDto {
    private OrderDto orderDto;
}
