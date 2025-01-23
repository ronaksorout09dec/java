package INTERN;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class To_Do_Application extends JFrame {
    private DefaultListModel<Task> listModel;
    private JList<Task> todoList;
    private JTextField taskInput;
    private JSpinner timeSpinner;
    private JComboBox<String> monthComboBox;
    private JSpinner daySpinner;
    private JSpinner yearSpinner;

    @SuppressWarnings("unused")
    public To_Do_Application() {
        setTitle("Todo List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        listModel = new DefaultListModel<>();
        todoList = new JList<>(listModel);
        taskInput = new JTextField(20);

        // Date and Time Components
        monthComboBox = new JComboBox<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        daySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1)); // Days from 1 to 31
        yearSpinner = new JSpinner(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR), 1900, 2100, 1)); // Year from 1900 to 2100

        // Time Spinner
        Date initialTime = Calendar.getInstance().getTime();
        timeSpinner = new JSpinner(new SpinnerDateModel(initialTime, null, null, Calendar.MINUTE)); // Step by minute
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm"); 
        timeSpinner.setEditor(timeEditor);

        // Add Change Listeners 
        timeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Update the time display if needed
                JSpinner source = (JSpinner) e.getSource();
                Date date = (Date) source.getValue();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                System.out.println("Time changed to: " + sdf.format(date));
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(taskInput);
        inputPanel.add(monthComboBox);
        inputPanel.add(daySpinner);
        inputPanel.add(yearSpinner);
        inputPanel.add(timeSpinner);

        // Button Panel with GridLayout
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10)); // 1 row, 3 columns
        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete");
        JButton editButton = new JButton("Edit");

        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());
        editButton.addActionListener(e -> editTask());

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);

        // Set equal size for buttons
        Dimension buttonSize = new Dimension(100, 40);
        for (Component comp : buttonPanel.getComponents()) {
            comp.setPreferredSize(buttonSize);
        }

        add(new JScrollPane(todoList), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(600, 500);
        setLocationRelativeTo(null);
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            String month = (String) monthComboBox.getSelectedItem();
            int day = (int) daySpinner.getValue();
            int year = (int) yearSpinner.getValue();
            String time = timeSpinner.getValue().toString();

            Task newTask = new Task(task, month, day, year, time);
            listModel.addElement(newTask);
            taskInput.setText("");
        }
    }

    private void deleteTask() {
        int selectedIndex = todoList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
        }
    }

    private void editTask() {
        int selectedIndex = todoList.getSelectedIndex();
        if (selectedIndex != -1) {
            Task selectedTask = listModel.get(selectedIndex);
            String newTask = JOptionPane.showInputDialog(this, "Edit Task:", selectedTask.getDescription());
            if (newTask != null && !newTask.trim().isEmpty()) {
                selectedTask.setDescription(newTask.trim());
                listModel.set(selectedIndex, selectedTask);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new To_Do_Application().setVisible(true));
    }

    // Task class to hold task details
    static class Task {
        private String description;
        private String month;
        private int day;
        private int year;
        private String time;

        public Task(String description, String month, int day, int year, String time) {
            this.description = description;
            this.month = month;
            this.day = day;
            this.year = year;
            this.time = time;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description + " - " + month + " " + day + ", " + year + " at " + time;
        }
    }
}