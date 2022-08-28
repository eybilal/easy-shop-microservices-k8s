package io.coodle.easyshop.common.domain.dto.response;

import io.coodle.easyshop.common.domain.dto.response.CategoryResponseDto;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductResponseDto {
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private CategoryResponseDto category;
}
