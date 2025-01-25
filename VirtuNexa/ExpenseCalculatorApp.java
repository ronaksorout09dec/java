import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Expense {
    private String category;
    private double amount;
    private LocalDate date;

    public Expense(String category, double amount, LocalDate date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%s | $%.2f | %s", category, amount, date);
    }
}

public class ExpenseCalculatorApp {
    private static final List<Expense> expenses = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Financial Management App ---");
            System.out.println("1. Calculator\n2. Expense Manager\n3. Financial Analytics\n4. Exit");
            System.out.print("Choose an option: ");

            switch (getUserChoice()) {
                case 1 -> calculator();
                case 2 -> expenseManager();
                case 3 -> financialAnalytics();
                case 4 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void calculator() {
        while (true) {
            System.out.println("\n--- Calculator ---");
            System.out.println("1. Add\n2. Subtract\n3. Multiply\n4. Divide\n5. Back");
            System.out.print("Enter your choice: ");

            int choice = getUserChoice();
            if (choice == 5) return;
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice.");
                continue;
            }

            try {
                System.out.print("Enter First numbers: ");
                double num1 = scanner.nextDouble();
                System.out.print("Enter Second numbers: ");
                double num2 = scanner.nextDouble();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> System.out.printf("Result: %.2f%n", num1 + num2);
                    case 2 -> System.out.printf("Result: %.2f%n", num1 - num2);
                    case 3 -> System.out.printf("Result: %.2f%n", num1 * num2);
                    case 4 -> System.out.println(num2 != 0 ? "Result: %.2f".formatted(num1 / num2) : "Error: Division by zero");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                scanner.nextLine();
            }
        }
    }

    private static void expenseManager() {
        while (true) {
            System.out.println("\n--- Expense Manager ---");
            System.out.println("1. Add Expense\n2. View Expenses\n3. Edit Expense\n4. Delete Expense\n5. Back");
            System.out.print("Choose an option: ");

            switch (getUserChoice()) {
                case 1 -> addExpense();
                case 2 -> viewExpenses();
                case 3 -> editExpense(); 
                case 4 -> deleteExpense();
                case 5 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void addExpense() {
        try {
            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(scanner.nextLine(), DATE_FORMATTER);
            expenses.add(new Expense(category, amount, date));
            System.out.println("Expense added.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            System.out.println("\nExpenses:");
            for (int i = 0; i < expenses.size(); i++) {
                System.out.printf("%d. %s%n", i, expenses.get(i));
            }
        }
    }

    private static void deleteExpense() {
        viewExpenses();
        if (expenses.isEmpty()) return;

        System.out.print("Enter the index to delete: ");
        try {
            int index = Integer.parseInt(scanner.nextLine());
            if (index >= 0 && index < expenses.size()) {
                System.out.println("Removed: " + expenses.remove(index));
            } else {
                System.out.println("Invalid index.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void editExpense() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses available to edit.");
            return;
        }

        viewExpenses(); 
        System.out.print("\nEnter the index of the expense to edit (0-based): ");

        try {
            int index = scanner.nextInt();
            scanner.nextLine(); 

            if (index < 0 || index >= expenses.size()) {
                System.out.println("Invalid index. Please try again.");
                return;
            }

            Expense currentExpense = expenses.get(index);

            System.out.println("\nCurrent Expense Details: " + currentExpense);
            System.out.println("Enter new details (leave blank to keep the current value):");

            System.out.print("New Category [" + currentExpense.getCategory() + "]: ");
            String newCategory = scanner.nextLine().trim();
            if (newCategory.isEmpty()) {
                newCategory = currentExpense.getCategory();
            }

            System.out.print("New Amount [" + currentExpense.getAmount() + "]: ");
            String newAmountStr = scanner.nextLine().trim();
            double newAmount = currentExpense.getAmount();
            if (!newAmountStr.isEmpty()) {
                try {
                    newAmount = Double.parseDouble(newAmountStr);
                    if (newAmount <= 0) {
                        System.out.println("Amount must be positive. Keeping the current amount.");
                        newAmount = currentExpense.getAmount();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount format. Keeping the current amount.");
                }
            }

            System.out.print("New Date [" + currentExpense.getDate() + "] (yyyy-MM-dd): ");
            String newDateStr = scanner.nextLine().trim();
            LocalDate newDate = currentExpense.getDate();
            if (!newDateStr.isEmpty()) {
                try {
                    newDate = LocalDate.parse(newDateStr, DATE_FORMATTER);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Keeping the current date.");
                }
            }

            expenses.set(index, new Expense(newCategory, newAmount, newDate));
            System.out.println("Expense updated successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid index.");
            scanner.nextLine(); 
        } catch (Exception e) {
            System.out.println("An error occurred while editing the expense: " + e.getMessage());
        }
    }

    private static void financialAnalytics() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to analyze.");
            return;
        }

        double total = expenses.stream().mapToDouble(Expense::getAmount).sum();
        Map<String, Double> categoryTotals = new HashMap<>();

        for (Expense e : expenses) {
            categoryTotals.merge(e.getCategory(), e.getAmount(), Double::sum);
        }

        System.out.printf("Total Expenses: $%.2f%n", total);
        System.out.println("\nBy Category:");
        categoryTotals.forEach((cat, amt) -> System.out.printf("%s: $%.2f%n", cat, amt));

        categoryTotals.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry -> System.out.println("Most Expensive Category: " + entry.getKey()));
    }
}
