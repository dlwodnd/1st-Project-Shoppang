package com.project.test.product;

import com.project.test.common.Const;
import com.project.test.common.ResVo;
import com.project.test.exceptions.PurchaseProductException;
import com.project.test.product.models.dto.*;
import com.project.test.product.models.vo.ProductSelVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Tag(name = "장바구니", description = "장바구니관련 처리")
@Slf4j
public class ProductController {
    private final ProductService PRODUCT_SERVICE;

    @GetMapping
    @Parameters(value = {@Parameter(name = "userPk", description = "유저 pk값", required = true)
            , @Parameter(name = "choiceList", description = "장바구니 표시 설정.<br>" + "0 : 모든 상품 보기.(디폴트값)<br>" + "1 : 구매예정 상품만 보기.<br>" + "2 : 구매확정 상품만 보기.<br>", required = true)})
    @Operation(summary = "구매예정 상품 목록", description = "구매예정 상품 목록.")
    public List<ProductSelVo> getProductAll(ProductSelDto dto) {
        if(dto.getUserPk() == 2){
            List<ProductSelVo> productSelVos = new ArrayList<>();
            return productSelVos;
        }
        if (dto.getChoiceList() < 0 || dto.getChoiceList() > 2) {
            //입력받은 choiceList 값이 0 1 2 가 아니라면 디폴트 값인 0으로 변경
            dto.setChoiceList(0);
        }
        log.info("selDto : {}", dto);
        return PRODUCT_SERVICE.selProductList(dto);
    }

    @PostMapping
    @Operation(summary = "구매예정 상품 추가", description = "구매예정 상품 추가.")
    public ResVo postProduct(@RequestBody ProductInsDto dto) {
        String checkProductNm = dto.getProductNm().replaceAll(" ", "");
        if (checkProductNm.isEmpty()) {
            //입력받은 productNm 값이 공백제외 길이가 0 이라면 예외처리
            throw new PurchaseProductException("productNm은 필수 입력값입니다.");
        }
        log.info("insDto : {}", dto);
        ResVo result = PRODUCT_SERVICE.insProduct(dto);
        return result;

    }

    @PutMapping
    @Operation(summary = "구매예정 상품 수정", description = "구매예정 상품 수정.")
    public ResVo putProduct(@RequestBody ProductUpdDto dto) {
        String checkProductNm = dto.getProductNm().replaceAll(" ", "");
        if (checkProductNm.isEmpty()) {
            //입력받은 productNm 값이 공백제외 길이가 0 이라면 예외처리
            throw new PurchaseProductException("productNm은 필수 입력값입니다.");
        }
        ResVo result = PRODUCT_SERVICE.updProduct(dto);

        return result;
    }

    @PatchMapping
    @Operation(summary = "구매확정", description = "구매확정")
    @Parameters(value = {@Parameter(name = "userPk", description = "유저pk값", required = true),
            @Parameter(name = "productPk", description = "구매예정 상품pk값", required = true)})
    public ResVo checkProduct(ProductCheckDto dto) {
        log.info("checkDto : {} ",dto);
        ResVo result = PRODUCT_SERVICE.checkProduct(dto);
        return result;
    }

    @DeleteMapping
    @Operation(summary = "구매예정 상품 삭제", description = "구매예정 리스트에서 삭제.")
    @Parameters(value = {@Parameter(name = "userPk", description = "유저pk값", required = true),
            @Parameter(name = "productPk", description = "구매예정 상품pk값", required = true)})
    public ResVo deleteProduct(ProductDelDto dto) {
        ResVo result = PRODUCT_SERVICE.delProduct(dto);
        return result;
    }


}
