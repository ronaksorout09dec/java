package com.expensemanager.model;

import java.time.LocalDate;
import java.util.Objects;

public class Expense {
    private Long id;
    private double amount;
    private String category;
    private String description;
    private LocalDate date;

    public Expense(Long id, double amount, String category, String description, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    // Default constructor for database operations
    public Expense() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public String toString() {
        return String.format("Expense[id=%d, amount=%.2f, category='%s', description='%s', date=%s]",
                id, amount, category, description, date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Double.compare(expense.amount, amount) == 0 &&
                Objects.equals(id, expense.id) &&
                Objects.equals(category, expense.category) &&
                Objects.equals(description, expense.description) &&
                Objects.equals(date, expense.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, category, description, date);
    }
}