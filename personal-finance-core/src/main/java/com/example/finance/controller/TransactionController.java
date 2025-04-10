
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
        Summary summary = transactionService.calculateSummary(transactions); // ← add this line
        model.addAttribute("transactions", transactions);
        model.addAttribute("categories", categories);
        model.addAttribute("summary", summary); // ← and this line
        return "transactions";
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
        List<Transaction> transactions = transactionService.filterTransactions(category, startDate, endDate);
        List<Category> categories = categoryService.getAllCategories();
        Summary summary = transactionService.calculateSummary(transactions); // ← add this line
        model.addAttribute("transactions", transactions);
        model.addAttribute("categories", categories);
        model.addAttribute("summary", summary); // ← and this line
        return "transactions";
    }
    
    
}
