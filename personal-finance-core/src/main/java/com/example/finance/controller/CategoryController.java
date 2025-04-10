package com.example.finance.controller;

import com.example.finance.model.Category;
import com.example.finance.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("category", new Category());
        return "categories";
    }

    @PostMapping
    public String addCategory(@Valid @ModelAttribute Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "categories";
        }
        categoryService.save(category);
        return "redirect:/categories";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }

    @PostMapping("/edit")
    public String editCategory(@Valid @ModelAttribute Category category, BindingResult result) {
        if (!result.hasErrors()) {
            categoryService.save(category);
        }
        return "redirect:/categories";
    }
}
