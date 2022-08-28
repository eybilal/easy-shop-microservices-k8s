package io.coodle.easyshop.common.domain.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CategoryRequestDto {
    @NotBlank
    private String name;

    @NotNull
    private String description;
}
