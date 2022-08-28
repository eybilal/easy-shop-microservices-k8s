package io.coodle.easyshop.inventoryservice.domain.entity;

import io.coodle.easyshop.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Category extends BaseEntity {
    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "description")
    @NotNull
    private String description;

    // Important:
    // ----------
    // In case, you have this use case:
    // 1- You are serializing the entity to JSON for the REST response (and NOT using the DTO)
    // 2- You have a bidirectional relation between entities, such as Category and Product
    //
    // To avoid a cyclic reference between Category and Product, use this technique:
    //
    // Category Entity: ignore serializing products for Reading (GET operations):
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // Serialize Only for writing
    //
    // Product Entity: serialize Category entity
    //
    @OneToMany(
            mappedBy="category",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Collection<Product> products;
}
