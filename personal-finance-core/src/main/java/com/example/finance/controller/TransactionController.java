package com.example.finance.controller;

import com.example.finance.model.Transaction;
import com.example.finance.model.Category;
import com.example.finance.service.TransactionService;
import com.example.finance.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import com.example.finance.dto.Summary;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAllTransactions();
        List<Category> categories = categoryService.getAllCategories();
        Summary summary = transactionService.calculateSummary(transactions);
    
        model.addAttribute("transactions", transactions);
        model.addAttribute("categories", categories);
        model.addAttribute("summary", summary); 
        model.addAttribute("selectedCategory", null);
        model.addAttribute("startDate", null);
        model.addAttribute("endDate", null);
        return "transactions";
    }
    
    

    @GetMapping("/add-form")
    public String showAddForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "add_transaction";
    }
    
    @PostMapping("/add")
    public String addTransaction(@ModelAttribute Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return "redirect:/transactions";
    }
    

    @PostMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/transactions";
    }

    @PostMapping("/update")
    public String updateTransaction(@ModelAttribute Transaction transaction) {
        transactionService.updateTransaction(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/filter")
    public String filterTransactions(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model
    ) {
        System.out.println("Filter requested with category: " + category + ", startDate: " + startDate + ", endDate: " + endDate); // Debug logging
        List<Transaction> transactions = transactionService.filterTransactions(category, startDate, endDate);
        List<Category> categories = categoryService.getAllCategories();
        Summary summary = transactionService.calculateSummary(transactions);
        model.addAttribute("transactions", transactions);
        model.addAttribute("categories", categories);
        model.addAttribute("summary", summary);
        model.addAttribute("selectedCategory", category); 
        model.addAttribute("startDate", startDate); 
        model.addAttribute("endDate", endDate);     
        return "transactions";
    }
    
    
}
