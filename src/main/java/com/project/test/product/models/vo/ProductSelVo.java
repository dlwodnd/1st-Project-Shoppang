package com.project.test.product.models.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "구매예정 상품 출력 정보")
public class ProductSelVo {
    private int productPk;
    private int userPk;
    private String productNm;
    private int categoryPk;
    private String categoryNm;
    private String memo;
    private String createdAt;
    private int buyingCheck;
    private String buyingDate;
}
