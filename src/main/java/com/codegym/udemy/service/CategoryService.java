package com.codegym.udemy.service;

import com.codegym.udemy.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategory();
    void editCategory(Long categoryId, CategoryDto categoryDto);
    void deleteCategory(Long categoryId);
}
