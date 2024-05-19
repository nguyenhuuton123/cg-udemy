package com.codegym.udemy.service.impl;

import com.codegym.udemy.dto.CategoryDto;
import com.codegym.udemy.entity.Category;
import com.codegym.udemy.entity.Course;
import com.codegym.udemy.repository.CategoryRepository;
import com.codegym.udemy.repository.CourseRepository;
import com.codegym.udemy.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CourseRepository courseRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    private Category convertToCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        List<Course> courses = new ArrayList<>();
        if (categoryDto.getCoursesId() != null) {
            for (Long courseId : categoryDto.getCoursesId()
            ) {
                Optional<Course> optionalCourse = courseRepository.findById(courseId);
                optionalCourse.ifPresent(courses::add);
                // Optionally handle the case where a course with the given ID doesn't exist
                if (optionalCourse.isEmpty()) {
                    throw new IllegalArgumentException("Course with ID " + courseId + " not found.");
                }
            }
        }
        category.setCourses(courses);
        return category;
    }

    private CategoryDto convertToCategoryDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        List<Long> coursesId = category.getCourses().stream().map(Course::getId).toList();
        categoryDto.setCoursesId(coursesId);
        return categoryDto;
    }

    @Override
    public void createCategory(CategoryDto categoryDto) {
        Category category = convertToCategory(categoryDto);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::convertToCategoryDto).collect(Collectors.toList());
    }

    @Override
    public void editCategory(Long categoryId, CategoryDto categoryDto) {
        // Fetch the existing Category entity from the database
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + categoryId));

        // Update the fields of the existing Category entity with the data from the CategoryDto
        modelMapper.map(categoryDto, existingCategory);

        // Update courses if necessary
        List<Course> courses = new ArrayList<>();
        if (categoryDto.getCoursesId() != null) {
            for (Long courseId : categoryDto.getCoursesId()) {
                Optional<Course> optionalCourse = courseRepository.findById(courseId);
                optionalCourse.ifPresent(courses::add);
            }
        }
        existingCategory.setCourses(courses);

        // Save the updated Category entity back to the database
        categoryRepository.save(existingCategory);
    }


    @Override
    public void deleteCategory(Long categoryId) {
        // Check if the category exists
        if (!categoryRepository.existsById(categoryId)) {
            throw new IllegalArgumentException("Category not found with ID: " + categoryId);
        }

        // Delete the category from the database
        categoryRepository.deleteById(categoryId);
    }
}
