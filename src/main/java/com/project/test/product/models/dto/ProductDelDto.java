package com.project.test.product.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "구매예정 상품 삭제 필요 정보")
public class ProductDelDto {
    @Schema(description = "유저pk값")
    private int userPk;
    @Schema(description = "구매예정 상품pk값")
    private int productPk;
}
