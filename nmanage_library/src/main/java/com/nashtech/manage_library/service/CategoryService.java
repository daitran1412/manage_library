package com.nashtech.manage_library.service;

import java.util.List;

import com.nashtech.manage_library.Entity.ListBook.Category;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Long categoryId);

    Category createCategory(Category category);

    void deleteCategory(Long categoryId);
    
}
