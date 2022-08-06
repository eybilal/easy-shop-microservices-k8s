package io.coodle.inventoryservice.entity;


import io.coodle.inventoryservice.pojo.DescriptiveEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class Product extends DescriptiveEntity {
    private double price;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
