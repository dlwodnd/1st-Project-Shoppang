package com.project.test.product.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductSelDto {
    @Schema(description = "유저 pk값")
    private int userPk;
    @Schema(description = "구매예정 상품 필터링. Default = 0. 0 : 모두보기, 1 : 구매예정 상품만 보기, 2 : 구매확정 상품만 보기")
    private int choiceList;
}
