# Expense Tracker and Arithmetic Operations Application

## Overview

This is a Java-based GUI application that combines two core functionalities:

1. **Basic Arithmetic Operations**: Perform addition, subtraction, multiplication, and division.
2. **Expense Tracker**: Manage personal expenses with features to add, edit, delete, and categorize expenses. Includes data persistence using SQLite and a GUI interface built with JavaFX.

---

## Features

### Arithmetic Operations

- Addition, subtraction, multiplication, and division.
- User-friendly GUI panel for entering numbers and viewing results.
- Input validation to handle errors like division by zero.

### Expense Tracker

- Add, edit, and delete expenses with details such as description, category, amount, and date.
- Categorize expenses (e.g., Food, Travel, Utilities).
- View a list of all expenses with filtering options.
- Database integration for persistent storage of expenses using SQLite.

### Additional Features

- Export reports to Excel (optional for future expansion).
- Logging using Log4j2.

---

## Technologies Used

- **Java 17**: Core language for the application.
- **JavaFX**: For creating the GUI interface.
- **SQLite**: For data persistence.
- **Maven**: For project dependency management.
- **Log4j2**: For logging.

---

## Project Structure

```
expense-tracker/
├── pom.xml                           # Maven configuration file
├── README.md                         # Project documentation
├── database_schema.sql               # SQL file to set up the database
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── expensetracker/
│   │   │           ├── Main.java                       # Entry point of the application
│   │   │           ├── config/AppConfig.java           # Configuration class
│   │   │           ├── controller/                    
│   │   │           │   ├── ArithmeticController.java   # Logic for arithmetic operations
│   │   │           │   └── ExpenseController.java      # Logic for expense management
│   │   │           ├── model/
│   │   │           │   ├── Expense.java                # Expense entity class
│   │   │           │   └── Category.java               # Category entity class
│   │   │           ├── repository/ExpenseRepository.java
│   │   │           ├── service/
│   │   │           │   ├── ArithmeticService.java      # Business logic for arithmetic
│   │   │           │   └── ExpenseService.java         # Business logic for expenses
│   │   │           ├── ui/
│   │   │           │   ├── MainWindow.java             # Main GUI application
│   │   │           │   └── components/
│   │   │           │       ├── ArithmeticPanel.java    # GUI for arithmetic
│   │   │           │       └── ExpensePanel.java       # GUI for expense tracker
│   │   │           └── util/
│   │   │               ├── DatabaseConnector.java      # Database connection utility
│   │   │               ├── CurrencyFormatter.java      # Utility for formatting currency
│   │   │               └── ValidationUtil.java         # Utility for input validation
│   └── resources/
│       ├── application.properties                      # App configuration
│       ├── log4j2.xml                                  # Logging configuration
│       └── categories.json                             # Default expense categories
└── test/
    └── java/
        └── com/
            └── expensetracker/
                ├── ArithmeticServiceTest.java
                ├── ExpenseServiceTest.java
                └── DatabaseConnectorTest.java
```

---

## Setup and Installation

### Prerequisites

- Install **Java 17** or later.
- Install **Maven**.
- Install **SQLite3** (optional for testing the database outside the app).

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/your-repo/expense-tracker.git
   cd expense-tracker
   ```

2. Set up the database:

   ```bash
   sqlite3 expense_tracker.db < database_schema.sql
   ```

3. Build the project:

   ```bash
   mvn clean package
   ```

4. Run the application:

   ```bash
   java -jar target/expense-tracker-1.0-SNAPSHOT.jar
   ```

---

## Usage

### Arithmetic Operations

1. Navigate to the **Arithmetic Operations** tab in the application.
2. Enter two numbers and select an operation (Add, Subtract, Multiply, Divide).
3. View the result in the output field.

### Expense Tracker

1. Navigate to the **Expense Tracker** tab.
2. Add a new expense by filling out the form (description, category, amount, date).
3. View the list of expenses and delete/edit as needed.
4. Data is saved automatically in the SQLite database.

---

## Testing

Run the unit tests to ensure functionality:

```bash
mvn test
```

---

## Future Enhancements

- Add functionality to export expense data to Excel using Apache POI.
- Add functionality to import/export data in JSON format.
- Implement graphical reports for expense summaries.

---

## License

This project is licensed under the MIT License.

---

## Contributors

- Your Name ([your.email@example.com](mailto\:your.email@example.com))

