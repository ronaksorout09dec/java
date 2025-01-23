package INTERN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calculator extends JFrame {
    private JTextField displayField;
    private StringBuilder currentInput = new StringBuilder();
    private double firstNumber = 0;
    private String currentOperator = "";
    private boolean isNewNumber = true;
    private boolean hasResult = false;

    public calculator() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayField = new JTextField("0");
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.BOLD, 60));
        displayField.setPreferredSize(new Dimension(0, 100));
        displayField.setBackground(Color.BLACK);
        displayField.setForeground(Color.WHITE);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(Color.WHITE);

        String[][] buttonLabels = {
            {"AC", "+/-", "%", "/"},
            {"7", "8", "9", "*"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "+"},
            {"0", "", ".", "="}
        };

        Color operatorColor = new Color(255, 165, 0);
        Color specialColor = Color.DARK_GRAY;
        Color numberColor = Color.BLACK;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        for (int row = 0; row < buttonLabels.length; row++) {
            for (int col = 0; col < buttonLabels[row].length; col++) {
                String label = buttonLabels[row][col];
                if (label.isEmpty()) {
                    continue;
                }

                JButton button = new JButton(label);
                button.setFont(new Font("Arial", Font.PLAIN, 20));
                button.setPreferredSize(new Dimension(80, 80));

                if (label.equals("AC") || label.equals("+/-") || label.equals("%")) {
                    button.setBackground(specialColor);
                    button.setForeground(Color.WHITE);
                } else if ("*/+-=".contains(label)) {
                    button.setBackground(operatorColor);
                    button.setForeground(Color.WHITE);
                } else {
                    button.setBackground(numberColor);
                    button.setForeground(Color.WHITE);
                }

                if (label.equals("0")) {
                    gbc.gridwidth = 2;
                    button.setPreferredSize(new Dimension(170, 80));
                } else {
                    gbc.gridwidth = 1;
                }

                gbc.gridx = col;
                gbc.gridy = row;

                button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                button.setFocusPainted(false);
                button.setContentAreaFilled(true);

                button.addActionListener(new ButtonClickListener());
                buttonPanel.add(button, gbc);

                if (label.equals("0")) {
                    col++;
                }
            }
        }

        add(buttonPanel, BorderLayout.CENTER);
        setSize(500, 700);
        setLocationRelativeTo(null);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();

            if (command.matches("[0-9.]")) {
                if (isNewNumber || hasResult) {
                    currentInput.setLength(0);
                    isNewNumber = false;
                    hasResult = false;
                }

                if (command.equals(".") && currentInput.toString().contains(".")) {
                    return;
                }
                
                currentInput.append(command);
                displayField.setText(currentInput.toString());
            } else if (command.equals("AC")) {
                reset();
            } else if (command.equals("+/-")) {
                if (currentInput.length() > 0) {
                    double value = Double.parseDouble(currentInput.toString());
                    value = -value; 
                    currentInput.setLength(0);
                    currentInput.append(value);
                    displayField.setText(currentInput.toString());
                }
            } else if (command.equals("%")) {
                
                String input = JOptionPane.showInputDialog("Enter percentage:");
                if (input != null && !input.isEmpty()) {
                    try {
 double percentage = Double.parseDouble(input);
                        if (currentInput.length() > 0) {
                            double value = Double.parseDouble(currentInput.toString());
                            value = value * (percentage / 100);
                            currentInput.setLength(0);
                            currentInput.append(value);
                            displayField.setText(currentInput.toString());
                        }
                    } catch (NumberFormatException e) {
                        displayField.setText("Invalid input");
                    }
                }
            } else if ("+-*/".contains(command)) {
                if (currentInput.length() > 0) {
                    if (!currentOperator.isEmpty()) {
                        calculateResult();
                    }
                    firstNumber = Double.parseDouble(currentInput.toString());
                    currentOperator = command;
                    isNewNumber = true;
                }
            } else if (command.equals("=")) {
                calculateResult();
                currentOperator = "";
                hasResult = true;
            }
        }
    }

    private void calculateResult() {
        if (!currentOperator.isEmpty() && currentInput.length() > 0) {
            double secondNumber = Double.parseDouble(currentInput.toString());
            double result = 0;

            switch (currentOperator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        displayField.setText("Error");
                        reset();
                        return;
                    }
                    break;
            }

            String resultStr = (result == (long) result) ?
                String.format("%d", (long) result) :
                String.format("%s", result);

            currentInput.setLength(0);
            currentInput.append(resultStr);
            displayField.setText(resultStr);
            firstNumber = result;
            isNewNumber = true;
        }
    }

    private void reset() {
        currentInput.setLength(0);
        firstNumber = 0;
        currentOperator = "";
        isNewNumber = true;
        hasResult = false;
        displayField.setText("0");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            calculator calculator = new calculator();
            calculator.setVisible(true);
        });
    }
}