package com.project.test.product.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "구매예정 상품 추가시 필요 정보")
public class ProductInsDto {
    @Schema(description = "유저 pk값")
    private int userPk;
    @Schema(description = "카테고리 pk값",defaultValue = "1")
    private int categoryPk;
    @Schema(description = "구매예정 상품명",defaultValue = "상품명")
    private String productNm;
    @Schema(description = "구매예정 상품 메모",defaultValue = "메모")
    private String memo;
}
