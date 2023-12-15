package com.project.test.product;

import com.project.test.category.mapper.CategoryMapper;
import com.project.test.category.models.entity.CategoryEntity;
import com.project.test.common.Const;
import com.project.test.common.ResVo;
import com.project.test.exceptions.PurchaseProductException;
import com.project.test.product.mapper.ProductMapper;
import com.project.test.product.models.dto.ProductInsDto;
import com.project.test.product.models.dto.ProductSelDto;
import com.project.test.product.models.dto.ProductUpdDto;
import com.project.test.product.models.entity.ProductEntity;
import com.project.test.product.models.vo.ProductSelVo;
import com.project.test.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductMapper PRODUCT_MAPPER;
    private final CategoryMapper CATEGORY_MAPPER;
    private final UserMapper USER_MAPPER;

    //구매예정 상품 불러오기
    public List<ProductSelVo> selProductList(ProductSelDto dto){
        if(USER_MAPPER.checkUserPk(dto.getUserPk()) == null){
            throw new PurchaseProductException("잘못된 userPK 값이 입력되었습니다.","E1001");
        }else if(dto.getChoiceList() < 0 || dto.getChoiceList() > 2){
            //입력받은 choiceList 값이 0 1 2 가 아니라면 디폴트 값인 0으로 변경
            dto.setChoiceList(0);
        }
        return PRODUCT_MAPPER.selProductList(dto);
    }
    //구매예정 상품 추가
    public ResVo insProduct(ProductInsDto dto){
        String checkProductNm = dto.getProductNm().replaceAll(" ","");
        CategoryEntity category = CATEGORY_MAPPER.checkCategory(dto.getCategoryPk());
        if(USER_MAPPER.checkUserPk(dto.getUserPk()) == null){
            //입력받은 userPk값이 없는 값이라면 UNKNOWN_PK=(3)으로 리턴
            throw new PurchaseProductException("잘못된 userPK 값이 입력되었습니다.","E1001");
        }
        else if (checkProductNm.isEmpty()){
            //입력받은 productNm 값이 공백제외 길이가 0 이라면 BLANK=(4)로 리턴
            throw new PurchaseProductException("productNm 값은 필수 입력값입니다.","E1002");
        }
        else if(category == null){
            //입력받은 categoryPk값이 없는 값이라면 UNKNOWN_PK=(3)으로 리턴
            throw new PurchaseProductException("잘못된 categoryPK 값이 입력되었습니다.","E1003");
        }
        if(dto.getMemo().replaceAll(" ", "").isEmpty()){
            //받아온 memo 값이 공백으로만 이루어져 있다면 빈값으로 변경
            dto.setMemo("");
        }
        int result = PRODUCT_MAPPER.insProduct(dto);
        return new ResVo(result);
    }
    //구매예정 상품 수정
    public ResVo updProduct(ProductUpdDto dto){
        String checkProductNm = dto.getProductNm().replaceAll(" ","");
        CategoryEntity category = CATEGORY_MAPPER.checkCategory(dto.getCategoryPk());
        if(dto.getUserPk() < 1){
            //입력받은 userPk값이 없는 값이라면 UNKNOWN_PK=(3)으로 리턴
            throw new PurchaseProductException("잘못된 userPK 값이 입력되었습니다.","E1001");
        }
        else if (checkProductNm.isEmpty()){
            //입력받은 productNm 값이 공백제외 길이가 0 이라면 BLANK=(4)로 리턴
            throw new PurchaseProductException("productNm 값은 필수 입력값입니다.","E1011");
        }
        else if(category == null){
            //입력받은 categoryPk값이 없는 값이라면 UNKNOWN_PK=(3)으로 리턴
            throw new PurchaseProductException("잘못된 categoryPK 값이 입력되었습니다.","E1003");
        }
        if(dto.getMemo().replaceAll(" ", "").isEmpty()){
            //받아온 memo 값이 공백으로만 이루어져 있다면 빈값으로 변경
            dto.setMemo("");
        }
        int result = PRODUCT_MAPPER.updProduct(dto);
        return new ResVo(result);
    }
    //구매예정 상품 구매확정 처리
    public ResVo checkProduct(int productPk){
        ProductEntity productEntity = PRODUCT_MAPPER.checkProductPk(productPk);
        if(productEntity == null){
            //입력받은 productPk 값이 DB에 없는 값이라면 UNKNOWN_PK=(3)으로 리턴
            throw new PurchaseProductException("잘못된 productPk값을 입력했습니다.","E1010");
        }else if(productEntity.getBuyingCheck() == 1 || productEntity.getBuyingCheck() == 2){
            //입력된 productPk값에서 나온 buyingCheck 값이 이미 구매 완료 처리를 했거나 숨김 상태라명 FAIL
            throw new PurchaseProductException("이미 구매 확정된 productPk 입니다.","E1111");
        }
        int result = PRODUCT_MAPPER.checkProduct(productPk);
        return new ResVo(result);
    }
    //구매예정(확정) 상품 리스트에서 삭제
    //구매예정 상품은 데이터 삭제
    //구매확정 상품은 데이터는 보존, 리스트에서 숨김 처리
    public ResVo delProduct(List<Integer> productPkList){
        List<ProductEntity> productEntity = PRODUCT_MAPPER.checkProductPkList(productPkList);
        log.info("productEntity : {}",productEntity);
        if(productEntity.size() != productPkList.size()){
            //productPkList안에 잘못된 productPk값이 있는지 확인
            throw new PurchaseProductException("잘못된 productPk값을 입력했습니다.","E1010");
        }
        for(ProductEntity entity : productEntity){
            if(entity == null){
                //productPkList의 productPk 값이 테이블에 있는 값인지 확인
                throw new PurchaseProductException("잘못된 productPk값을 입력했습니다.","E1010");
            }else if(entity.getBuyingCheck() < 0 || entity.getBuyingCheck() > 1){
                throw new PurchaseProductException("이미 삭제처리된 productPk값을 포함하고 있습니다.","E1234");
            }
        }
        PRODUCT_MAPPER.hiedProduct(productPkList);
        PRODUCT_MAPPER.delProduct(productPkList);
        return new ResVo(Const.SUCCESS);
    }

}
