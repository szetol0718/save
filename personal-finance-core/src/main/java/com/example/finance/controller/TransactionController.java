
package com.example.finance.controller;

import com.example.finance.model.Transaction;
import com.example.finance.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public String listTransactions(Model model) {
        model.addAttribute("transactions", transactionService.getAll());
        return "transactions";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "transaction_form";
    }

    @PostMapping("/save")
    public String saveTransaction(@ModelAttribute Transaction transaction) {
        transactionService.save(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/edit/{id}")
    public String editTransaction(@PathVariable Long id, Model model) {
        model.addAttribute("transaction", transactionService.getById(id));
        return "transaction_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.delete(id);
        return "redirect:/transactions";
    }
}
