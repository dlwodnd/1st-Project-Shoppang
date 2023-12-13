package com.project.test.category.models.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CategoryEntity {
    @Schema(description = "카테고리 pk값")
    private int categoryPk;
    @Schema(description = "카테고리 이름")
    private String categoryNm;
}
