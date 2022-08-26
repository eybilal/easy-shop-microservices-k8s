package io.coodle.easyshop.inventoryservice.domain.entity;

import io.coodle.easyshop.common.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String name;

    @Column(name = "description")
    private String description;

    // To avoid a cyclic reference between Category and Product, due to the following facts:
    // 1- We are serializing the entity to JSON for the REST response (and NOT the DTO)
    // 2- We have a bidirectional relation between Category and Product
    // Category Entity: we ignore serializing products for Reading (GET operations)
    // Product Entity: we serialize Category entity
    @OneToMany(
            mappedBy="category",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // Serialize Only for writing
    private Collection<Product> products;

}
