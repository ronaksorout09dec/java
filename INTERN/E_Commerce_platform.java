package INTERN;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

// Product class
@SuppressWarnings("unused")
class Product {
    private String id;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;

    public Product(String id, String name, double price, String description, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public int getStockQuantity() { return stockQuantity; }

    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
}

// User class
class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
}

// Main Application Class
public class E_Commerce_platform extends JFrame {
    private Map<String, Product> products = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    @SuppressWarnings("unused")
    private User currentUser;

    private DefaultTableModel productTableModel;
    private DefaultTableModel cartTableModel;

    private JTable productTable;
    private JTable cartTable;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public E_Commerce_platform() {
        // Add products
        addSampleProducts();

        // Configure main window
        setTitle("E-commerce Platform");
        setSize(400, 400); // Adjusted for compact view
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize GUI
        showLoginScreen();
    }

    private void addSampleProducts() {
        products.put("P1", new Product("P1", "Laptop", 999.99, "High-performance laptop", 10));
        products.put("P2", new Product("P2", "Smartphone", 499.99, "Latest smartphone", 20));
        products.put("P3", new Product("P3", "Headphones", 99.99, "Wireless headphones", 30));
        products.put("P4", new Product("P4", "Smartwatch", 199.99, "Stylish smartwatch", 15));
    }

    @SuppressWarnings("unused")
    private void showLoginScreen() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS)); // Vertical layout
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Adjust height

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(e -> handleLogin());

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
        signUpButton.addActionListener(e -> showSignUpScreen());

        // Button Panel with spacing and alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center alignment with gap
        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        loginPanel.add(buttonPanel);  // Add the button panel

        setContentPane(loginPanel);
        revalidate();
    }

    @SuppressWarnings("unused")
    private void showSignUpScreen() {
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS)); // Vertical layout
        signUpPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField newUsernameField = new JTextField();
        newUsernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton signUpButton = new JButton("Create Account");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
        signUpButton.addActionListener(e -> handleSignUp(newUsernameField.getText(), newPasswordField.getPassword(), emailField.getText()));

        // Button Panel for sign up screen
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center alignment with gap
        buttonPanel.add(signUpButton);

        signUpPanel.add(usernameLabel);
        signUpPanel.add(newUsernameField);
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        signUpPanel.add(passwordLabel);
        signUpPanel.add(newPasswordField);
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        signUpPanel.add(emailLabel);
        signUpPanel.add(emailField);
        signUpPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        signUpPanel.add(buttonPanel); // Add the button panel

        setContentPane(signUpPanel);
        revalidate();
    }

    private void handleSignUp(String username, char[] password, String email) {
        if (username.isEmpty() || password.length == 0 || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (users.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String passwordStr = new String(password);
            User newUser = new User(username, passwordStr, email);
            users.put(username, newUser);
            JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showLoginScreen();  // Go back to login screen
        }
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            currentUser = users.get(username);
            showMainScreen();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unused")
    private void showMainScreen() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Vertical layout
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Product Table
        JLabel productLabel = new JLabel("Available Products:");
        productLabel.setFont(new Font("Arial", Font.BOLD, 16));

        String[] productColumns = {"ID", "Name", "Price", "Stock"};
        productTableModel = new DefaultTableModel(productColumns, 0);
        productTable = new JTable(productTableModel);
        productTable.setFont(new Font("Arial", Font.PLAIN, 12));
        productTable.setRowHeight(20);
        updateProductTable();

        JScrollPane productScrollPane = new JScrollPane(productTable);
        productScrollPane.setPreferredSize(new Dimension(350, 150)); // Compact height

        // Cart Table
        JLabel cartLabel = new JLabel("Your Cart:");
        cartLabel.setFont(new Font("Arial", Font.BOLD, 16));

        String[] cartColumns = {"Name", "Price", "Quantity", "Total"};
        cartTableModel = new DefaultTableModel(cartColumns, 0);
        cartTable = new JTable(cartTableModel);
        cartTable.setFont(new Font("Arial", Font.PLAIN, 12));
        cartTable.setRowHeight(20);

        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartScrollPane.setPreferredSize(new Dimension(350, 150));

        // Buttons for the main screen
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setFont(new Font("Arial", Font.BOLD, 14));
        addToCartButton.addActionListener(e -> addToCart());

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        checkoutButton.addActionListener(e -> checkout());

        // Button Panel for main screen
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(addToCartButton);
        buttonPanel.add(checkoutButton);

        mainPanel.add(productLabel);
        mainPanel.add(productScrollPane);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        mainPanel.add(cartLabel);
        mainPanel.add(cartScrollPane);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        mainPanel.add(buttonPanel); // Add the button panel

        setContentPane(mainPanel);
        revalidate();
    }

    private void updateProductTable() {
        productTableModel.setRowCount(0);
        for (Product product : products.values()) {
            Object[] row = {product.getId(), product.getName(), product.getPrice(), product.getStockQuantity()};
            productTableModel.addRow(row);
        }
    }

    private void addToCart() {
        int row = productTable.getSelectedRow();
        if (row != -1) {
            String productId = (String) productTable.getValueAt(row, 0);
            Product product = products.get(productId);
            // Simulating adding to cart (just for demonstration)
            Object[] cartRow = {product.getName(), product.getPrice(), 1, product.getPrice()};
            cartTableModel.addRow(cartRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to add to cart!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkout() {
        double total = 0;
        for (int i = 0; i < cartTableModel.getRowCount(); i++) {
            total += (double) cartTableModel.getValueAt(i, 3);
        }
        JOptionPane.showMessageDialog(this, "Total Checkout Amount: $" + total, "Checkout", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            E_Commerce_platform gui = new E_Commerce_platform();
            gui.setVisible(true);
        });
    }
}
