package com.eybilal.orderservice.model.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.OffsetDateTime;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@SuperBuilder
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(updatable = false)
    protected OffsetDateTime createdDate;

    protected OffsetDateTime lastModifiedDate;
}
