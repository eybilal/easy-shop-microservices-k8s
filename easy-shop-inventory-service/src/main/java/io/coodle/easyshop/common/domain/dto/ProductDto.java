package io.coodle.easyshop.common.domain.dto;

import io.coodle.easyshop.inventoryservice.domain.entity.Category;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductDto {
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    // private Long categoryId;

    private Category category;
}
