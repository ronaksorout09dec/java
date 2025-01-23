package com.expensemanager.dao;

import com.expensemanager.model.Expense;
import com.expensemanager.util.DatabaseConfig;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {
    
    public void addExpense(Expense expense) throws SQLException {
        String sql = "INSERT INTO expenses (amount, category, description, date) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setDouble(1, expense.getAmount());
            pstmt.setString(2, expense.getCategory());
            pstmt.setString(3, expense.getDescription());
            pstmt.setString(4, expense.getDate().toString());
            
            pstmt.executeUpdate();
            
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    expense.setId(generatedKeys.getLong(1));
                }
            }
        }
    }
    
    public void updateExpense(Expense expense) throws SQLException {
        String sql = "UPDATE expenses SET amount = ?, category = ?, description = ?, date = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, expense.getAmount());
            pstmt.setString(2, expense.getCategory());
            pstmt.setString(3, expense.getDescription());
            pstmt.setString(4, expense.getDate().toString());
            pstmt.setLong(5, expense.getId());
            
            pstmt.executeUpdate();
        }
    }
    
    public void deleteExpense(Long id) throws SQLException {
        String sql = "DELETE FROM expenses WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }
    
    public Expense getExpenseById(Long id) throws SQLException {
        String sql = "SELECT * FROM expenses WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToExpense(rs);
                }
            }
        }
        return null;
    }
    
    public List<Expense> getAllExpenses() throws SQLException {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM expenses ORDER BY date DESC";
        
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                expenses.add(mapResultSetToExpense(rs));
            }
        }
        return expenses;
    }
    
    private Expense mapResultSetToExpense(ResultSet rs) throws SQLException {
        return new Expense(
            rs.getLong("id"),
            rs.getDouble("amount"),
            rs.getString("category"),
            rs.getString("description"),
            LocalDate.parse(rs.getString("date"))
        );
    }
}