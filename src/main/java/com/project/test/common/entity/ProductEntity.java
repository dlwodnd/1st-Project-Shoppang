package com.project.test.common.entity;

import lombok.Data;

@Data
public class ProductEntity {
    private int userPk;
    private int categoryPk;
    private String productNm;
    private String memo;
    private String createdAt;
    private int buyingCheck;
    private String buyingDate;
}
