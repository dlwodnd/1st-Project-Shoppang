package com.project.test.category;

import com.project.test.category.models.dto.CategoryInsDto;
import com.project.test.common.entity.CategoryEntity;
import com.project.test.common.ResVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryMapper CATEGORY_MAPPER;

    //카테고리 불러오기
    public List<CategoryEntity> getCategoryAll (){
        return CATEGORY_MAPPER.getCategory();
    }
    public ResVo insCategory(CategoryInsDto dto){
        int result = CATEGORY_MAPPER.insCategory(dto);
        return new ResVo(result);
    }
}
