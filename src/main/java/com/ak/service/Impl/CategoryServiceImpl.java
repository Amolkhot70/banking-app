package com.ak.service.Impl;

import com.ak.Exception.NotFoundException;
import com.ak.dto.CategoryDto;
import com.ak.entity.Category;
import com.ak.mapper.CategoryMapper;
import com.ak.repository.CategoryRepository;
import com.ak.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
//        convert CategoryDto to Category entity
        Category category = CategoryMapper.mapToCategory(categoryDto);

//        save category object into database table -categories
        Category savedCategory = this.categoryRepository.save(category);

//        convert savedCategory to CategoryDto
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Category is not present with id : " + id)
                );
//        convert Category to CategoryDto and return
       return CategoryMapper.mapToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return this.categoryRepository.findAll().stream().map(CategoryMapper::mapToCategoryDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category is not present with id : " + categoryId));

        category.setName(categoryDto.getName());
        Category updatedCategory = this.categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(updatedCategory);

    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category is not present with id : " + categoryId));
        this.categoryRepository.delete(category);
    }
}
