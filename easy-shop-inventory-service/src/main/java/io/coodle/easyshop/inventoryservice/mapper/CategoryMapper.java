package io.coodle.easyshop.inventoryservice.mapper;

import io.coodle.easyshop.common.domain.dto.request.CategoryRequestDto;
import io.coodle.easyshop.common.domain.dto.response.CategoryResponseDto;
import io.coodle.easyshop.inventoryservice.domain.entity.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryResponseDto categoryToCategoryResponseDto(Category category);

    Category categoryRequestDtoToCategory(CategoryRequestDto categoryRequestDto);
}
