package com.project.test.category.mapper;

import com.project.test.category.models.dto.CategoryInsDto;
import com.project.test.category.models.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryEntity> getCategory();
    CategoryEntity checkCategory(int categoryPk);
    int insCategory(CategoryInsDto dto);
}
