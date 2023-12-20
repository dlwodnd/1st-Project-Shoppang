package com.project.test.product;

import com.project.test.category.CategoryMapper;
import com.project.test.common.entity.CategoryEntity;
import com.project.test.common.Const;
import com.project.test.common.ResVo;
import com.project.test.exceptions.PurchaseProductException;
import com.project.test.product.models.dto.*;
import com.project.test.common.entity.ProductEntity;
import com.project.test.product.models.vo.ProductSelVo;
import com.project.test.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
            throw new PurchaseProductException("잘못된 userPK 값이 입력되었습니다.");
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
            //입력받은 userPk값이 없는 값이라면 예외처리
            throw new PurchaseProductException("잘못된 userPK 값이 입력되었습니다.");
        }
        else if (checkProductNm.isEmpty()){
            //입력받은 productNm 값이 공백제외 길이가 0이라면
            throw new PurchaseProductException("productNm은 필수 입력값입니다.");
        }
        else if(category == null){
            //입력받은 categoryPk값이 없는 값이라면 예외처리
            throw new PurchaseProductException("잘못된 categoryPK 값이 입력되었습니다.");
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
        ProductEntity productEntity = PRODUCT_MAPPER.checkProductPk(dto.getProductPk());
        CategoryEntity category = CATEGORY_MAPPER.checkCategory(dto.getCategoryPk());
        if (productEntity.getProductPk() != dto.getProductPk()){
            throw new PurchaseProductException("잘못된 productPk 값이 입력되었습니다.");
        }
        else if(dto.getUserPk() != productEntity.getUserPk()){
            //입력받은 userPk값이 없는 값이라면 예외처리
            throw new PurchaseProductException("잘못된 userPK 값이 입력되었습니다.");
        }
        else if (checkProductNm.isEmpty()){
            //입력받은 productNm 값이 공백제외 길이가 0 이라면 예외처리
            throw new PurchaseProductException("productNm은 필수 입력값입니다.");
        }
        else if(category == null){
            //입력받은 categoryPk값이 없는 값이라면 예외처리
            throw new PurchaseProductException("잘못된 categoryPK 값이 입력되었습니다.");
        }
        if(dto.getMemo().replaceAll(" ", "").isEmpty()){
            //받아온 memo 값이 공백으로만 이루어져 있다면 빈값으로 변경
            dto.setMemo("");
        }
        int result = PRODUCT_MAPPER.updProduct(dto);
        return new ResVo(result);
    }
    //구매예정 상품 구매확정 처리
    public ResVo checkProduct(ProductCheckDto dto){
        ProductEntity productEntity = PRODUCT_MAPPER.checkProductPk(dto.getProductPk());
        if(productEntity == null){
            //입력받은 productPk 값이 DB에 없는 값이라면 예외처리
            throw new PurchaseProductException("잘못된 productPk값을 입력했습니다.");
        } else if (productEntity.getUserPk() != dto.getUserPk()) {
            //입력받은 productPK 값이 userPk가 작성한 값이 아니라면 예외처리
            throw new PurchaseProductException("입력한 productPk값은 다른 유저의 값 입니다.");
        } else if(productEntity.getBuyingCheck() == 1 || productEntity.getBuyingCheck() == 2){
            //입력된 productPk값에서 나온 buyingCheck 값이 이미 구매 완료 처리를 했거나 숨김 상태라면 예외처리
            throw new PurchaseProductException("이미 구매 확정된 productPk값 입니다.");
        }
        int result = PRODUCT_MAPPER.checkProduct(dto);
        return new ResVo(result);
    }
    //구매예정(확정) 상품 리스트에서 삭제
    //구매예정 상품은 데이터 삭제
    //구매확정 상품은 데이터는 보존, 리스트에서 숨김 처리
    public ResVo delProduct(ProductDelDto dto){
        ProductEntity productEntity = PRODUCT_MAPPER.checkProductPk(dto.getProductPk());
        log.info("productEntity : {}",productEntity);

        if(productEntity.getUserPk() != dto.getUserPk()){
            //입력받은 productPK 값이 userPk가 작성한 값이 아니라면 예외처리
            throw new PurchaseProductException("입력받은 productPk값은 다른 유저가 작성한 productPk값입니다.");
        }
        else if(productEntity.getBuyingCheck() == 2){
            throw new PurchaseProductException("잘못된 productPk값을 입력했습니다.");
        }

        PRODUCT_MAPPER.hiedProduct(dto);
        PRODUCT_MAPPER.delProduct(dto);
        return new ResVo(Const.SUCCESS);
    }

}
