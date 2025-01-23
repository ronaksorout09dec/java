package com.expensemanager;

import com.expensemanager.model.Expense;
import com.expensemanager.service.ExpenseService;
import com.expensemanager.util.DatabaseConfig;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public class ExpenseManagerCLI {
    private static final ExpenseService expenseService = new ExpenseService();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        DatabaseConfig.initializeDatabase();
        
        while (true) {
            printMenu();
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1 -> addExpense();
                    case 2 -> viewAllExpenses();
                    case 3 -> updateExpense();
                    case 4 -> deleteExpense();
                    case 5 -> viewExpensesByCategory();
                    case 6 -> viewDailyExpenses();
                    case 7 -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Expense Manager ===");
        System.out.println("1. Add Expense");
        System.out.println("2. View All Expenses");
        System.out.println("3. Update Expense");
        System.out.println("4. Delete Expense");
        System.out.println("5. View Expenses by Category");
        System.out.println("6. View Daily Expenses");
        System.out.println("7. Exit");
    }

    private static void addExpense() throws SQLException {
        System.out.println("\n=== Add New Expense ===");
        double amount = getDoubleInput("Enter amount: ");
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        LocalDate date = getDateInput("Enter date (yyyy-MM-dd): ");

        expenseService.addExpense(amount, category, description, date);
        System.out.println("Expense added successfully!");
    }

    private static void viewAllExpenses() throws SQLException {
        System.out.println("\n=== All Expenses ===");
        for (Expense expense : expenseService.getAllExpenses()) {
            System.out.printf("ID: %d | Amount: $%.2f | Category: %s | Description: %s | Date: %s%n",
                    expense.getId(), expense.getAmount(), expense.getCategory(),
                    expense.getDescription(), expense.getDate());
        }
        System.out.printf("Total Expenses: $%.2f%n", expenseService.getTotalExpenses());
    }

    private static void updateExpense() throws SQLException {
        System.out.println("\n=== Update Expense ===");
        Long id = (long) getIntInput("Enter expense ID to update: ");
        Expense expense = expenseService.getExpenseById(id);
        
        if (expense == null) {
            System.out.println("Expense not found!");
            return;
        }

        System.out.println("Current expense details:");
        System.out.printf("Amount: $%.2f | Category: %s | Description: %s | Date: %s%n",
                expense.getAmount(), expense.getCategory(), expense.getDescription(), expense.getDate());

        double amount = getDoubleInput("Enter new amount (press Enter to keep current): ");
        System.out.print("Enter new category (press Enter to keep current): ");
        String category = scanner.nextLine();
        System.out.print("Enter new description (press Enter to keep current): ");
        String description = scanner.nextLine();
        LocalDate date = getDateInput("Enter new date (yyyy-MM-dd, press Enter to keep current): ");

        if (amount == -1) amount = expense.getAmount();
        if (category.isEmpty()) category = expense.getCategory();
        if (description.isEmpty()) description = expense.getDescription();
        if (date == null) date = expense.getDate();

        expenseService.updateExpense(id, amount, category, description, date);
        System.out.println("Expense updated successfully!");
    }

    private static void deleteExpense() throws SQLException {
        System.out.println("\n=== Delete Expense ===");
        Long id = (long) getIntInput("Enter expense ID to delete: ");
        expenseService.deleteExpense(id);
        System.out.println("Expense deleted successfully!");
    }

    private static void viewExpensesByCategory() throws SQLException {
        System.out.println("\n=== Expenses by Category ===");
        Map<String, Double> categoryExpenses = expenseService.getExpensesByCategory();
        for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
            System.out.printf("%s: $%.2f%n", entry.getKey(), entry.getValue());
        }
    }

    private static void viewDailyExpenses() throws SQLException {
        System.out.println("\n=== Daily Expenses ===");
        Map<LocalDate, Double> dailyExpenses = expenseService.getDailyExpenses();
        for (Map.Entry<LocalDate, Double> entry : dailyExpenses.entrySet()) {
            System.out.printf("%s: $%.2f%n", entry.getKey(), entry.getValue());
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            
            if (input.isEmpty()) {
                return -1;
            }
            
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static LocalDate getDateInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            
            if (input.isEmpty()) {
                return null;
            }
            
            try {
                return LocalDate.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date in yyyy-MM-dd format.");
            }
        }
    }
}