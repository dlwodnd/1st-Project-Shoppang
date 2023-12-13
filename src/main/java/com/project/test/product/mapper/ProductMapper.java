package com.project.test.product.mapper;

import com.project.test.product.models.dto.ProductInsDto;
import com.project.test.product.models.dto.ProductSelDto;
import com.project.test.product.models.dto.ProductUpdDto;
import com.project.test.product.models.vo.ProductSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductSelVo> selProductList(ProductSelDto dto);
    int insProduct(ProductInsDto dto);
    int updProduct(ProductUpdDto dto);
    int checkProduct(int productPk);
    int delProduct(List<Integer> productPkList);
    int hiedProduct(List<Integer> productPkList);


}
