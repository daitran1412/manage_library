package com.nashtech.manage_library.controller.ListBook;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.manage_library.Entity.ListBook.Category;
import com.nashtech.manage_library.dto.ListBook.CategoryDto;
import com.nashtech.manage_library.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private ModelMapper modelMapper;
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get")
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
        return categoryDtos;
    }

    @GetMapping("/get/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    @PostMapping("/create")
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category createdCategory = categoryService.createCategory(category);
        CategoryDto createdCategoryDto = modelMapper.map(createdCategory, CategoryDto.class);
        return createdCategoryDto;
    }

    @PostMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

}
