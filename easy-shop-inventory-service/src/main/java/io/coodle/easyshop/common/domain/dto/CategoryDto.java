package io.coodle.easyshop.common.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CategoryDto {
    private Long id;

    private String name;

    private String description;
}
