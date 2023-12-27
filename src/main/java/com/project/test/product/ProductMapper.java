package com.project.test.product;

import com.project.test.product.models.dto.*;
import com.project.test.common.entity.ProductEntity;
import com.project.test.product.models.vo.ProductSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductSelVo> selProductList(ProductSelDto dto);

    int insProduct(ProductInsDto dto);

    int updProduct(ProductUpdDto dto);

    int checkProduct(ProductCheckDto dto);

    int delProduct(ProductDelDto dto);

    int hideProduct(ProductDelDto dto);
    int returnProduct(ProductCheckDto dto);

    ProductEntity checkProductPk(int productPk);


}
