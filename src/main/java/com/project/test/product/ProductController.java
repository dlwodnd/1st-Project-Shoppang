package com.project.test.product;

import com.project.test.common.ResVo;
import com.project.test.product.models.dto.ProductInsDto;
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
    @Parameters(value = {@Parameter(name = "user_pk", description = "유저 pk값")
                        ,@Parameter(name = "choice_list" , description = "장바구니 표시 설정" + "1 : 구매예정 상품만 보기\n" + "2 : 구매확정 상품만 보기")})

    @Operation(summary = "구매예정 상품 목록",description = "구매예정 상품 목록")
    public List<ProductSelVo> getProductAll(@RequestParam("user_pk")int userPk
                            , @RequestParam("choice_list")int choiceList){

        return PRODUCT_SERVICE.selProductList(userPk,choiceList);
    }
    @PostMapping
    @Operation(summary = "구매예정 상품 추가",description = "구매예정 상품 추가")
    public ResVo postProduct(@RequestBody ProductInsDto dto){
        return PRODUCT_SERVICE.insProduct(dto);

    }
    @PutMapping
    @Operation(summary = "구매예정 상품 수정",description = "구매예정 상품 수정")
    public ResVo putProduct(@RequestBody ProductUpdDto dto){
        return PRODUCT_SERVICE.updProduct(dto);
    }
    @PatchMapping
    @Operation(summary = "구매확정",description = "구매확정")
    @Parameter(name = "product_pk", description = "구매예정 상품 pk값")
    public ResVo checkProduct(@RequestParam("product_pk")int productPk){
        return PRODUCT_SERVICE.checkProduct(productPk);
    }
    @DeleteMapping
    @Operation(summary = "구매예정 상품 삭제",description = "구매예정 리스트에서 삭제")
    public ResVo deleteProduct(@RequestParam("product_pk")List<Integer> productPkList){
        return PRODUCT_SERVICE.delProduct(productPkList);
    }

}
