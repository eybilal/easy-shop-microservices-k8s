package io.coodle.easyshop.inventoryservice.domain.entity;


import io.coodle.easyshop.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String name;

    @Column(name = "description")
    private String description;

    private BigDecimal price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
