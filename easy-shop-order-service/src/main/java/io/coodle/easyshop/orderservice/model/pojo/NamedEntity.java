package io.coodle.easyshop.orderservice.model.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class NamedEntity extends BaseEntity {
    @Column(name = "name")
    @NotEmpty
    private String name;
}
