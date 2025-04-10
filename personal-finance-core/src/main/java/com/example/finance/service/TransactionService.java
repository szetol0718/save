package com.example.finance.service;

import com.example.finance.dto.Summary;
import com.example.finance.model.Transaction;
import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions();

    void saveTransaction(Transaction transaction);

    void deleteTransaction(Long id);

    void updateTransaction(Transaction transaction);

    List<Transaction> filterTransactions(String category, LocalDate startDate, LocalDate endDate);

    Summary calculateSummary(List<Transaction> transactions);  // ✅ NEW METHOD
}

