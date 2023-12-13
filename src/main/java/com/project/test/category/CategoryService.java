package com.project.test.category;

import com.project.test.category.mapper.CategoryMapper;
import com.project.test.category.models.entity.CategoryEntity;
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
}
