package com.eybilal.customerservice.model.entity;

import com.eybilal.customerservice.model.pojo.NamedEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@SuperBuilder
public class Customer extends NamedEntity {

    @NotBlank
    @Email
    private String email;
}
