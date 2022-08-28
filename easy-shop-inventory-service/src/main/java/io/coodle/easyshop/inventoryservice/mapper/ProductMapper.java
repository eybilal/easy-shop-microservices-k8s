package io.coodle.easyshop.inventoryservice.mapper;

import io.coodle.easyshop.common.domain.dto.ProductDto;
import io.coodle.easyshop.inventoryservice.domain.entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);
}
