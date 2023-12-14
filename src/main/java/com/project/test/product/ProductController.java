package com.project.test.product;

import com.project.test.common.Const;
import com.project.test.common.ResVo;
import com.project.test.exceptions.PurchaseProductException;
import com.project.test.product.models.dto.ProductInsDto;
import com.project.test.product.models.dto.ProductSelDto;
import com.project.test.product.models.dto.ProductUpdDto;
import com.project.test.product.models.vo.ProductSelVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Tag(name = "장바구니",description = "장바구니관련 처리")
@Slf4j
public class ProductController {
    private final ProductService PRODUCT_SERVICE;

    @GetMapping
    @Parameters(value = {@Parameter(name = "userPk", description = "유저 pk값",required = true)
                        ,@Parameter(name = "choiceList" , description = "장바구니 표시 설정.<br>" +"0 : 모든 상품 보기.(디폴트값)<br>"+ "1 : 구매예정 상품만 보기.<br>" + "2 : 구매확정 상품만 보기.<br>",required = true)
                        ,@Parameter(name = "selectDate", description = "보고 싶은 날짜 선택.<br> 입력방식 : yyyy-mm-dd ")})
    @Operation(summary = "구매예정 상품 목록",description = "구매예정 상품 목록.")
    public List<ProductSelVo> getProductAll(ProductSelDto dto){
        log.info("selDto : {}",dto);
        List<ProductSelVo> voList = PRODUCT_SERVICE.selProductList(dto);
        if(voList == null){
            throw new PurchaseProductException("존재하지 않는 pk값이 입력되었습니다.");
        }

        return PRODUCT_SERVICE.selProductList(dto);
    }
    @PostMapping
    @Operation(summary = "구매예정 상품 추가",description = "구매예정 상품 추가.")
    public ResVo postProduct(@RequestBody ProductInsDto dto){
        log.info("dto : {}",dto);
        ResVo result = PRODUCT_SERVICE.insProduct(dto);
        if(result.getResult() == Const.UNKNOWN_PK){
            throw new PurchaseProductException("존재하지 않는 pk값이 입력되었습니다.");
        }else if(result.getResult() == Const.EMPTY){
            throw new PurchaseProductException("구매예정 물건이름을 입력하지 않았습니다.");
        }
        return result;

    }
    @PutMapping
    @Operation(summary = "구매예정 상품 수정",description = "구매예정 상품 수정.")
    public ResVo putProduct(@RequestBody ProductUpdDto dto){
        ResVo result = PRODUCT_SERVICE.updProduct(dto);
        if(result.getResult() == Const.UNKNOWN_PK){
            throw new PurchaseProductException("존재하지 않는 pk값이 입력되었습니다.");
        }else if(result.getResult() == Const.EMPTY){
            throw new PurchaseProductException("구매예정 물건이름을 입력하지 않았습니다.");
        }
        return result;
    }
    @PatchMapping
    @Operation(summary = "구매확정",description = "구매확정")
    @Parameter(name = "productPk", description = "구매예정 상품 pk값.")
    public ResVo checkProduct(@RequestParam("productPk")int productPk){
        ResVo result = PRODUCT_SERVICE.checkProduct(productPk);
        if(result.getResult() == Const.FAIL){
            throw new PurchaseProductException("이미 구해확정된 상품입니다.");
        }else if(result.getResult() == Const.UNKNOWN_PK){
            throw new PurchaseProductException("존재하지 않는 pk값이 입력되었습니다.");
        }
        return result;
    }
    @DeleteMapping
    @Operation(summary = "구매예정 상품 삭제",description = "구매예정 리스트에서 삭제")
    @Parameter(name = "productPk", description = "삭제할 상품 pk값.<br>" + "pk값 1개이상 입력 가능.")
    public ResVo deleteProduct(@RequestParam("productPk")List<Integer> productPkList){
        ResVo result = PRODUCT_SERVICE.delProduct(productPkList);
        if (result.getResult() == Const.UNKNOWN_PK){
            throw new PurchaseProductException("잘못된 PK값을 입력했습니다.");
        }else if(result.getResult() == Const.FAIL){
            throw new PurchaseProductException("이미 숨김 처리된 pk값이 존재합니다.");
        }
        return result;
    }



}
