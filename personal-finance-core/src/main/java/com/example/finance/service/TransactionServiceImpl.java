package com.example.finance.service;

import com.example.finance.dto.Summary;
import com.example.finance.model.Transaction;
import com.example.finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> filterTransactions(String category, LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findAll().stream()
                .filter(t -> (category == null || t.getCategory().equalsIgnoreCase(category)) &&
                             (startDate == null || !t.getDate().isBefore(startDate)) &&
                             (endDate == null || !t.getDate().isAfter(endDate)))
                .collect(Collectors.toList());
    }

    @Override
    public Summary calculateSummary(List<Transaction> transactions) {
        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction t : transactions) {
            if ("income".equalsIgnoreCase(t.getType())) {
                totalIncome += t.getAmount();
            } else if ("expense".equalsIgnoreCase(t.getType())) {
                totalExpense += t.getAmount();
            }
        }

        double netBalance = totalIncome - totalExpense;
        return new Summary(totalIncome, totalExpense, netBalance);
    }
}
