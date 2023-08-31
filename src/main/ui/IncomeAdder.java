package ui;

import model.SingleEntry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// the income edit tab that allows for addition of new income entries
public class IncomeAdder extends JPanel {
    private static final String TITLE = "Add entry";
    private final BudgetAppUI controller;
    private JLabel top;

    private JTextField nameField;
    private JTextField amountField;
    private JTextField dateField;

    private JLabel name;
    private JLabel amount;
    private JLabel date;

    // EFFECTS: constructs the income edit tab
    public IncomeAdder(BudgetAppUI controller) {
        this.controller = controller;
        setLayout(new GridLayout(5, 2));
        createDisplay();
        addSubmitButton();
    }

    // EFFECTS: creates the text panels and title for the entry adder
    private void createDisplay() {
        nameField = new JTextField(30);
        amountField = new JTextField(30);
        dateField = new JTextField(30);
        name = new JLabel("Name:");
        amount = new JLabel("Amount:");
        date = new JLabel("Date(YYYY-MM-DD):");
        addTitle();
        add(new JLabel());
        add(name);
        add(nameField);
        add(amount);
        add(amountField);
        add(date);
        add(dateField);
    }

    // MODIFIES: this
    // EFFECTS: adds a submit button
    private void addSubmitButton() {
        JButton submitButton = new JButton("Add");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    double amount = Double.parseDouble(amountField.getText());
                    String date = dateField.getText();
                    controller.getBudget().addIncomeEntry(new SingleEntry(name, amount, date));
                    controller.getIncomeTab().addRow(new SingleEntry(name, amount, date));
                    nameField.setText("");
                    amountField.setText("");
                    dateField.setText("");
                } catch (NumberFormatException ex) {
                    // nothing happens
                }
            }
        });

        add(new JLabel());
        add(submitButton);
    }

    // EFFECTS: creates a title for the app
    private void addTitle() {
        top = new JLabel(TITLE, JLabel.CENTER);
        top.setSize(WIDTH, HEIGHT / 3);
        this.add(top);
    }
}