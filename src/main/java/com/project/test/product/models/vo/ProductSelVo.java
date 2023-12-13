package com.project.test.product.models.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "구매예정 상품 출력 정보")
public class ProductSelVo {
    @Schema(description = "구매예정 상품 pk값")
    private int productPk;
    @Schema(description = "유저 pk값")
    private int userPk;
    @Schema(description = "구매예정 상품명")
    private String productNm;
    @Schema(description = "카테고리 이름")
    private String categoryNm;
    @Schema(description = "구매예정 상품 메모")
    private String memo;
    @Schema(description = "구매예정 상품 등록 날짜/시간")
    private String createdAt;
    @Schema(description = "구매예정 상품 구매 여부. 0 : 구매예정, 1 : 구매확정, 2 : 구매확정 숨김")
    private int buyingCheck;
    @Schema(description = "구매확정 날짜")
    private String buyingDate;
}
