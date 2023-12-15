package com.project.test.product.models.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "구매예정 상품 리스트 출력 필요정보")
public class ProductSelDto {
    @Schema(description = "유저 pk값",defaultValue = "1")
    private int userPk;
    @Schema(description = "구매예정 상품 필터링. Default = 0. 0 : 모두보기, 1 : 구매예정 상품만 보기, 2 : 구매확정 상품만 보기",defaultValue = "0")
    private int choiceList;
    @Schema(description = "보고싶은 날짜 정보.입력방식 : yyyy-mm-dd",defaultValue = "yyyy-mm-dd")
    private String selectDate;
}
