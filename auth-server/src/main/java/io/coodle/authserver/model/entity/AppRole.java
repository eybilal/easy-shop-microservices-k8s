package io.coodle.authserver.model.entity;

import io.coodle.authserver.model.pojo.NamedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SuperBuilder
public class AppRole extends NamedEntity {
    // O DO NOT need a bidirectional relation
    // I DO NOT need from Role to know which users have this Role
}
