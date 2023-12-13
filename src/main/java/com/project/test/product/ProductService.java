package com.project.test.product;

import com.project.test.common.Const;
import com.project.test.common.ResVo;
import com.project.test.product.mapper.ProductMapper;
import com.project.test.product.models.dto.ProductInsDto;
import com.project.test.product.models.dto.ProductSelDto;
import com.project.test.product.models.dto.ProductUpdDto;
import com.project.test.product.models.vo.ProductSelVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper PRODUCT_MAPPER;

    //구매예정 상품 불러오기
    public List<ProductSelVo> selProductList(int userPk,int choiceList){
        ProductSelDto dto = new ProductSelDto();
        dto.setChoiceList(choiceList);
        dto.setUserPk(userPk);
        return PRODUCT_MAPPER.selProductList(dto);
    }
    //구매예정 상품 추가
    public ResVo insProduct(ProductInsDto dto){
        int result = PRODUCT_MAPPER.insProduct(dto);
        return new ResVo(result);
    }
    //구매예정 상품 수정
    public ResVo updProduct(ProductUpdDto dto){
        int result = PRODUCT_MAPPER.updProduct(dto);
        return new ResVo(result);
    }
    //구매예정 상품 구매확정 처리
    public ResVo checkProduct(int productPk){
        int result = PRODUCT_MAPPER.checkProduct(productPk);
        return new ResVo(result);
    }
    //구매예정(확정) 상품 리스트에서 삭제
    //구매예정 상품은 데이터 삭제
    //구매확정 상품은 데이터는 보존, 리스트에서 숨김 처리
    public ResVo delProduct(List<Integer> productPkList){
        PRODUCT_MAPPER.hiedProduct(productPkList);
        PRODUCT_MAPPER.delProduct(productPkList);
        return new ResVo(Const.SUCCESS);
    }

}
