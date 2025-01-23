package INTERN;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private List<String> transactions;  // Now properly declared

    public Account(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();  // Initialize the List
        addTransaction("Initial deposit: $" + initialBalance);
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }
    public List<String> getTransactions() { return transactions; }  // Getter for transactions

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposit: $" + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            addTransaction("Withdrawal: $" + amount);
            return true;
        }
        return false;
    }

    private void addTransaction(String transaction) {
        this.transactions.add(transaction);  // Use this.transactions
    }
}

// Rest of the code remains exactly the same as in the previous version
class Bank {
    private Map<String, Account> accounts;
    private static int accountCounter = 1000;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public String createAccount(String accountHolder, double initialBalance) {
        String accountNumber = "ACC" + (++accountCounter);
        Account account = new Account(accountNumber, accountHolder, initialBalance);
        accounts.put(accountNumber, account);
        return accountNumber;
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean transfer(String fromAccount, String toAccount, double amount) {
        Account from = getAccount(fromAccount);
        Account to = getAccount(toAccount);
        
        if (from != null && to != null && from.withdraw(amount)) {
            to.deposit(amount);
            return true;
        }
        return false;
    }
}

public class Banking_App extends JFrame {
    private Bank bank;
    private JPanel mainPanel, cardPanel;
    private CardLayout cardLayout;
    
    @SuppressWarnings("unused")
    public Banking_App() {
        bank = new Bank();
        setTitle("Banking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        // Initialize panels
        mainPanel = new JPanel(new BorderLayout());
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        
        // Create menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(7, 1, 5, 5));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        String[] buttonLabels = {
            "Create New Account",
            "Check Balance",
            "Deposit",
            "Withdraw",
            "Transfer",
            "View Transactions",
            "Exit"
        };
        
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(e -> handleMenuAction(label));
            menuPanel.add(button);
        }
        
        // Create operation panels
        createOperationPanels();
        
        // Add panels to frame
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
    
    @SuppressWarnings("unused")
    private void createOperationPanels() {
        // Create Account Panel
        JPanel createPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        JTextField nameField = new JTextField(20);
        JTextField amountField = new JTextField(20);
        JButton createButton = new JButton("Create Account");
        JLabel resultLabel = new JLabel("");
        
        createButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String accountNumber = bank.createAccount(name, amount);
                resultLabel.setText("Account created successfully! Account number: " + accountNumber);
                nameField.setText("");
                amountField.setText("");
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid amount!");
            }
        });
        
        gbc.gridx = 0; gbc.gridy = 0;
        createPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        createPanel.add(nameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        createPanel.add(new JLabel("Initial Amount:"), gbc);
        gbc.gridx = 1;
        createPanel.add(amountField, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        createPanel.add(createButton, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        createPanel.add(resultLabel, gbc);
        
        cardPanel.add(createPanel, "Create New Account");
        
        // Check Balance Panel
        JPanel balancePanel = createSimpleOperationPanel("Check Balance", 
            accountNumber -> {
                Account account = bank.getAccount(accountNumber);
                return account != null ? 
                    "Balance: $" + account.getBalance() : 
                    "Account not found!";
            });
        cardPanel.add(balancePanel, "Check Balance");
        
        // Deposit Panel
        JPanel depositPanel = createAmountOperationPanel("Deposit",
            (accountNumber, amount) -> {
                Account account = bank.getAccount(accountNumber);
                if (account != null) {
                    account.deposit(amount);
                    return "Deposit successful! New balance: $" + account.getBalance();
                }
                return "Account not found!";
            });
        cardPanel.add(depositPanel, "Deposit");
        
        // Withdraw Panel
        JPanel withdrawPanel = createAmountOperationPanel("Withdraw",
            (accountNumber, amount) -> {
                Account account = bank.getAccount(accountNumber);
                if (account != null) {
                    if (account.withdraw(amount)) {
                        return "Withdrawal successful! New balance: $" + account.getBalance();
                    }
                    return "Insufficient funds or invalid amount!";
                }
                return "Account not found!";
            });
        cardPanel.add(withdrawPanel, "Withdraw");
        
        // Transfer Panel
        JPanel transferPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        JTextField fromAccountField = new JTextField(20);
        JTextField toAccountField = new JTextField(20);
        JTextField transferAmountField = new JTextField(20);
        JButton transferButton = new JButton("Transfer");
        JLabel transferResultLabel = new JLabel("");
        
        transferButton.addActionListener(e -> {
            try {
                String fromAccount = fromAccountField.getText();
                String toAccount = toAccountField.getText();
                double amount = Double.parseDouble(transferAmountField.getText());
                
                if (bank.transfer(fromAccount, toAccount, amount)) {
                    transferResultLabel.setText("Transfer successful!");
                    fromAccountField.setText("");
                    toAccountField.setText("");
                    transferAmountField.setText("");
                } else {
                    transferResultLabel.setText("Transfer failed! Please check account numbers and balance.");
                }
            } catch (NumberFormatException ex) {
                transferResultLabel.setText("Please enter a valid amount!");
            }
        });
        
        gbc.gridx = 0; gbc.gridy = 0;
        transferPanel.add(new JLabel("From Account:"), gbc);
        gbc.gridx = 1;
        transferPanel.add(fromAccountField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        transferPanel.add(new JLabel("To Account:"), gbc);
        gbc.gridx = 1;
        transferPanel.add(toAccountField, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        transferPanel.add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1;
        transferPanel.add(transferAmountField, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        transferPanel.add(transferButton, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        transferPanel.add(transferResultLabel, gbc);
        
        cardPanel.add(transferPanel, "Transfer");
        
        // View Transactions Panel
        JPanel transactionsPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout());
        JTextField accNumberField = new JTextField(20);
        JButton viewButton = new JButton("View Transactions");
        JTextArea transactionsArea = new JTextArea(15, 40);
        transactionsArea.setEditable(false);
        
        viewButton.addActionListener(e -> {
            Account account = bank.getAccount(accNumberField.getText());
            if (account != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Transaction History for ").append(account.getAccountHolder()).append("\n\n");
                for (String transaction : account.getTransactions()) {
                    sb.append(transaction).append("\n");
                }
                transactionsArea.setText(sb.toString());
            } else {
                transactionsArea.setText("Account not found!");
            }
        });
        
        inputPanel.add(new JLabel("Account Number:"));
        inputPanel.add(accNumberField);
        inputPanel.add(viewButton);
        
        transactionsPanel.add(inputPanel, BorderLayout.NORTH);
        transactionsPanel.add(new JScrollPane(transactionsArea), BorderLayout.CENTER);
        
        cardPanel.add(transactionsPanel, "View Transactions");
    }
    
    @SuppressWarnings("unused")
    private JPanel createSimpleOperationPanel(String operation, OperationCallback callback) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        JTextField accountField = new JTextField(20);
        JButton actionButton = new JButton(operation);
        JLabel resultLabel = new JLabel("");
        
        actionButton.addActionListener(e -> {
            String result = callback.execute(accountField.getText());
            resultLabel.setText(result);
        });
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Account Number:"), gbc);
        gbc.gridx = 1;
        panel.add(accountField, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(actionButton, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(resultLabel, gbc);
        
        return panel;
    }
    
    @SuppressWarnings("unused")
    private JPanel createAmountOperationPanel(String operation, AmountOperationCallback callback) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        JTextField accountField = new JTextField(20);
        JTextField amountField = new JTextField(20);
        JButton actionButton = new JButton(operation);
        JLabel resultLabel = new JLabel("");
        
        actionButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                String result = callback.execute(accountField.getText(), amount);
                resultLabel.setText(result);
                if (result.contains("successful")) {
                    amountField.setText("");
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid amount!");
            }
        });
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Account Number:"), gbc);
        gbc.gridx = 1;
        panel.add(accountField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Amount:"), gbc);
        gbc.gridx = 1;
        panel.add(amountField, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(actionButton, gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(resultLabel, gbc);
        
        return panel;
    }
    
    private void handleMenuAction(String action) {
        if ("Exit".equals(action)) {
            System.exit(0);
        } else {
            cardLayout.show(cardPanel, action);
        }
    }
    
    @FunctionalInterface
    interface OperationCallback {
        String execute(String accountNumber);
    }
    
    @FunctionalInterface
    interface AmountOperationCallback {
        String execute(String accountNumber, double amount);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Banking_App().setVisible(true);
        });
    }
}