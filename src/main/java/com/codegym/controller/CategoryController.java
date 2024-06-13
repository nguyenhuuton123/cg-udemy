package com.codegym.t2dshop.controller;

import com.codegym.t2dshop.entity.Category;
import com.codegym.t2dshop.entity.Course;
import com.codegym.t2dshop.entity.Product;
import com.codegym.t2dshop.repository.CategoryRepository;
import com.codegym.t2dshop.repository.CourseRepository;
import com.codegym.t2dshop.repository.ProductRepository;
import com.codegym.t2dshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (Optional.ofNullable(category.getProducts()).isPresent()) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        return new ResponseEntity<>(category, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public List<String> getCategories() {
        return repository.findDistinctCategories();
    }

    @GetMapping("/exists")
    public ResponseEntity<List<Course>> checkCategoryExists(@RequestParam("category") String category) {
        List<Course> exists = repository.existsByName(category);
        return ResponseEntity.ok(exists);
    }
}

