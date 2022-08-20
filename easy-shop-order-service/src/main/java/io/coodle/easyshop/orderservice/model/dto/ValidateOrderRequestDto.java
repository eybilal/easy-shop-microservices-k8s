package io.coodle.easyshop.orderservice.model.dto;

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
