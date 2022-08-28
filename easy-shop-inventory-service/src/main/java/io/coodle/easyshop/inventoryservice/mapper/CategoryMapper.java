package io.coodle.easyshop.inventoryservice.mapper;

import io.coodle.easyshop.common.domain.dto.CategoryDto;
import io.coodle.easyshop.inventoryservice.domain.entity.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryDto categoryToCategoryDto(Category category);

    Category categoryDtoToCategory(CategoryDto categoryDto);
}
