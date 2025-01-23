package com.expensetracker;

import com.expensetracker.ui.ExpenseTrackerUI;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Expense Tracker!");
        ExpenseTrackerUI ui = new ExpenseTrackerUI();
        ui.start();
    }
}
