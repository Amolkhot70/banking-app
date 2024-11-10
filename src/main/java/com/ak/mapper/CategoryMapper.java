package com.ak.mapper;

import com.ak.dto.CategoryDto;
import com.ak.entity.Category;

public class CategoryMapper {

    //    Map CategoryDto to Category entity
    public static Category mapToCategory(CategoryDto categoryDto) {
        return new Category(
                categoryDto.getId(),
                categoryDto.getName()
        );
    }

    //    Map Category to CategoryDto
    public static CategoryDto mapToCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }

}
