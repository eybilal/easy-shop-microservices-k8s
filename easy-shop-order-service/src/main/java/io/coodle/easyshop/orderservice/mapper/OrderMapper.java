package io.coodle.easyshop.orderservice.mapper;

import io.coodle.easyshop.orderservice.model.dto.OrderDto;
import io.coodle.easyshop.orderservice.model.entity.Order;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    OrderDto orderToOrderDto(Order order);

    Order orderDtoToOrder(OrderDto orderDto);
}
