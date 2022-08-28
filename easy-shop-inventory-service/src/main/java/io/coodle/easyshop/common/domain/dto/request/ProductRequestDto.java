package io.coodle.easyshop.common.domain.dto.request;

import io.coodle.easyshop.inventoryservice.domain.entity.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductRequestDto {
    @NotBlank
    private String name;

    @NotNull
    private String description;

    @Positive
    private BigDecimal price;

    @Positive
    private Integer quantity;

    @NotNull
    private Long categoryId;
}
