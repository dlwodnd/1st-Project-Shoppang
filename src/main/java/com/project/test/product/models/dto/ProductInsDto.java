package com.project.test.product.models.dto;

import lombok.Data;

@Data
public class ProductInsDto {
    private int userPk;
    private int categoryPk;
    private String productNm;
    private String memo;
    private int productCount;
}
