package io.coodle.easyshop.inventoryservice.domain.entity;


import io.coodle.easyshop.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Product extends BaseEntity {
    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "description")
    @NotNull
    private String description;

    @Positive
    private Integer quantity;

    @Positive
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
