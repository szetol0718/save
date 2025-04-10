package com.example.finance.service;

import com.example.finance.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    void save(Category category);
    void delete(Long id);
    Category findById(Long id); // Optional if used later
    List<Category> getAllCategories();
}


