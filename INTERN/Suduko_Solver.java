package INTERN;

import javax.swing.*;
import java.awt.*;

public class Suduko_Solver extends JFrame {
    private JTextField[][] cells;
    private static final int GRID_SIZE = 9;
    
    @SuppressWarnings("unused")
    public Suduko_Solver() {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Create grid panel
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        cells = new JTextField[GRID_SIZE][GRID_SIZE];

        // Create cells
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cells[i][j] = new JTextField(1);
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setFont(new Font("Arial", Font.BOLD, 20));
                
                // Add border for 3x3 boxes
                if ((i % 3 == 0 && i != 0) || (j % 3 == 0 && j != 0)) {
                    cells[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                }
                
                gridPanel.add(cells[i][j]);
            }
        }

        // Create button panel
        JPanel buttonPanel = new JPanel();
        JButton solveButton = new JButton("Solve");
        JButton clearButton = new JButton("Clear");
        
        solveButton.addActionListener(e -> solveSudoku());
        clearButton.addActionListener(e -> clearGrid());
        
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 500);
        setLocationRelativeTo(null);
    }

    private void solveSudoku() {
        int[][] grid = new int[GRID_SIZE][GRID_SIZE];
        
        // Read input from cells
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                String value = cells[i][j].getText().trim();
                grid[i][j] = value.isEmpty() ? 0 : Integer.parseInt(value);
            }
        }

        if (solve(grid)) {
            // Update UI with solution
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    cells[i][j].setText(String.valueOf(grid[i][j]));
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No solution exists!");
        }
    }

    private boolean solve(int[][] grid) {
        int row = -1;
        int col = -1;
        boolean isEmpty = false;
        
        // Find empty cell
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = true;
                    break;
                }
            }
            if (isEmpty) break;
        }

        // No empty cell found
        if (!isEmpty) return true;

        // Try digits 1-9
        for (int num = 1; num <= GRID_SIZE; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;
                if (solve(grid)) return true;
                grid[row][col] = 0;
            }
        }
        return false;
    }

    private boolean isSafe(int[][] grid, int row, int col, int num) {
        // Check row
        for (int x = 0; x < GRID_SIZE; x++) {
            if (grid[row][x] == num) return false;
        }

        // Check column
        for (int x = 0; x < GRID_SIZE; x++) {
            if (grid[x][col] == num) return false;
        }

        // Check 3x3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) return false;
            }
        }

        return true;
    }

    private void clearGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cells[i][j].setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Suduko_Solver().setVisible(true);
        });
    }
}
