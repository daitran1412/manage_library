package com.nashtech.manage_library.controller.ListBook;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nashtech.manage_library.Entity.Error.Status;
import com.nashtech.manage_library.Entity.ListBook.Category;
import com.nashtech.manage_library.dto.ListBook.CategoryDto;
import com.nashtech.manage_library.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin
public class CategoryController {

    @Autowired
    private ModelMapper modelMapper;
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(categoryDtos);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            Status status = new Status();
            status.setMessage("Category not found");
            return ResponseEntity.ok(status);
        }
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return ResponseEntity.ok().body(categoryDto);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category createdCategory = categoryService.createCategory(category);
        CategoryDto createdCategoryDto = modelMapper.map(createdCategory, CategoryDto.class);
        return ResponseEntity.ok().body(createdCategoryDto);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            Status status = new Status();
            status.setMessage("Category not found");
            return ResponseEntity.ok(status);
        }
        categoryService.deleteCategory(id);
        Status status = new Status();
        status.setMessage("Delete category successfully");
        return ResponseEntity.ok(status);
    }

}
