
package com.example.finance.dto;

public class Summary {
    private double income;
    private double expense;
    private double netTotal;

    public Summary(double income, double expense, double netTotal) {
        this.income = income;
        this.expense = expense;
        this.netTotal = netTotal;
    }

    public double getIncome() {
        return income;
    }

    public double getExpense() {
        return expense;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }
}
