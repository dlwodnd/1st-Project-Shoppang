package com.project.test.category.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CategoryInsDto {
    @Schema(description = "카테고리 이름")
    private String categoryNm;
}
