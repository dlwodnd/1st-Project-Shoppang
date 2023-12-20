package com.project.test.category;

import com.project.test.category.models.dto.CategoryInsDto;
import com.project.test.common.entity.CategoryEntity;
import com.project.test.common.ResVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
@Slf4j
public class CategoryController {
    private final CategoryService CATEGORY_SERVICE;

    @GetMapping()
    @Operation(summary = "카테고리 가져오기", description = "카테고리 pk값과 이름을 정보.")
    public List<CategoryEntity> getCategoryAll() {
        return CATEGORY_SERVICE.getCategoryAll();
    }

    @PostMapping
    @Operation(summary = "카테고리 입력하기", description = "카테고리 입력")
    public ResVo insCategory(@RequestBody CategoryInsDto dto) {
        return CATEGORY_SERVICE.insCategory(dto);
    }
}
