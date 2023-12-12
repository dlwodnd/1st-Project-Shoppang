package com.project.test.product.models.vo;

import lombok.Data;

@Data
public class ProductSelVo {
    private int productPk;
    private int userPk;
    private String productNm;
    private String categoryNm;
    private String memo;
    private String createdAt;
    private int buyingCheck;
    private String buyingDate;
}
