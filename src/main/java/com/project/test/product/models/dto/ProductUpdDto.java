package com.project.test.product.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "구매예정 상품 수정 필요 정보")
public class ProductUpdDto {
    @Schema(description = "유저 pk값")
    private int userPk;
    @Schema(description = "카테고리 pk값")
    private int categoryPk;
    @Schema(description = "구매예정 상품명")
    private String productNm;
    @Schema(description = "구매예정 상품 메모")
    private String memo;
}
