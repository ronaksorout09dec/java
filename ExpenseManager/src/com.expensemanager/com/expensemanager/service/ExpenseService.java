package com.expensemanager.service;

import com.expensemanager.dao.ExpenseDAO;
import com.expensemanager.model.Expense;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseService {
    private final ExpenseDAO expenseDAO;
    
    public ExpenseService() {
        this.expenseDAO = new ExpenseDAO();
    }
    
    public void addExpense(double amount, String category, String description, LocalDate date) throws SQLException {
        Expense expense = new Expense(null, amount, category, description, date);
        expenseDAO.addExpense(expense);
    }
    
    public void updateExpense(Long id, double amount, String category, String description, LocalDate date) throws SQLException {
        Expense expense = new Expense(id, amount, category, description, date);
        expenseDAO.updateExpense(expense);
    }
    
    public void deleteExpense(Long id) throws SQLException {
        expenseDAO.deleteExpense(id);
    }
    
    public Expense getExpenseById(Long id) throws SQLException {
        return expenseDAO.getExpenseById(id);
    }
    
    public List<Expense> getAllExpenses() throws SQLException {
        return expenseDAO.getAllExpenses();
    }
    
    public Map<String, Double> getExpensesByCategory() throws SQLException {
        return getAllExpenses().stream()
            .collect(Collectors.groupingBy(
                Expense::getCategory,
                Collectors.summingDouble(Expense::getAmount)
            ));
    }
    
    public double getTotalExpenses() throws SQLException {
        return getAllExpenses().stream()
            .mapToDouble(Expense::getAmount)
            .sum();
    }
    
    public Map<LocalDate, Double> getDailyExpenses() throws SQLException {
        return getAllExpenses().stream()
            .collect(Collectors.groupingBy(
                Expense::getDate,
                Collectors.summingDouble(Expense::getAmount)
            ));
    }
}