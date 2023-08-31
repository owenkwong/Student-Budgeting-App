package ui.tabs;

import ui.BudgetAppUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeTab extends Tab {
    private static final String TITLE = "Student Budgeting Application";
    private JLabel top;
    private JLabel summary;
    private ImageIcon image;

    // EFFECTS: constructs a home tab with budget left and save/load
    public HomeTab(BudgetAppUI controller) {
        super(controller);
        setLayout(new GridLayout(4, 1));
        addTitle();
        addSummary();
        addSaveRetrieveButtons();
        addImage();
    }

    // EFFECTS: creates a title for the app
    private void addTitle() {
        top = new JLabel(TITLE, JLabel.CENTER);
        top.setSize(WIDTH, HEIGHT / 3);
        this.add(top);
    }

    // MODIFIES: this
    // EFFECTS: adds and resize image to home tab
    private void addImage() {
        image = new ImageIcon("data/budgetimage.jpeg");
        Image resize = image.getImage();
        int width = 400;
        int height = 300;
        Image resizedImage = resize.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(resizedImage);
        JLabel imageIcon = new JLabel(icon);
        add(imageIcon);
    }

    // EFFECTS: places save/load buttons that interact with current report
    private void addSaveRetrieveButtons() {
        JButton saveB = new JButton("Save reports to file");
        JButton retrieveB = new JButton("Retrieve budget reports from file");
        JPanel displayRow = formatButtonRow(saveB);
        displayRow.add(retrieveB);
        displayRow.setSize(WIDTH, HEIGHT / 4);

        saveB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().saveBudget();
            }
        });

        retrieveB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getController().retrieveBudget();
            }
        });

        this.add(displayRow);
    }

    // MODIFIES: this
    // EFFECTS: places the budget summary
    private void addSummary() {
        summary = new JLabel("Remaining budget: " + 0, JLabel.CENTER);
        summary.setSize(WIDTH, HEIGHT / 4);
        this.add(summary);
    }

    // MODIFIES: this
    // EFFECTS: updates the budget summary
    public void updateBudgetSummary() {
        getController().getBudget().updateOverallTotal();
        getController().getBudget().updateOverallTotal();
        summary.setText("Remaining budget: " + getController().getBudget().getOverallTotal());
    }

}
