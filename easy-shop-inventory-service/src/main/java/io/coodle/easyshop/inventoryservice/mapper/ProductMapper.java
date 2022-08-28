package io.coodle.easyshop.inventoryservice.mapper;

import io.coodle.easyshop.common.domain.dto.request.ProductRequestDto;
import io.coodle.easyshop.common.domain.dto.response.ProductResponseDto;
import io.coodle.easyshop.inventoryservice.domain.entity.Product;
import org.mapstruct.Mapper;

@Mapper(uses = CategoryMapper.class)
public interface ProductMapper {
    ProductResponseDto productToProductResponseDto(Product product);

    Product productRequestDtoToProduct(ProductRequestDto productRequestDto);
}
