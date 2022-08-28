package io.coodle.easyshop.common.domain.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CategoryResponseDto {
    private Long id;

    private String name;

    private String description;
}
