package ui.tabs;

import model.SingleEntry;
import ui.BudgetAppUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// This is the tab that displays the summary for income
public class IncomeReportTab extends Tab {
    private JScrollPane pane;
    private String title;
    private JLabel summary;
    private DefaultTableModel model;

    // EFFECTS: constructs an income report tab to display income report stats
    public IncomeReportTab(BudgetAppUI controller, String name) {
        super(controller);
        setLayout(new GridLayout(4, 1));
        this.title = name;

        addSummary();

        JPanel listBlock = new JPanel(new BorderLayout());
        String[] columnNames = { "Name", "Amount", "Date (YYYY-MM-DD)"};
        model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        pane = new JScrollPane(table);
        listBlock.add(pane, BorderLayout.CENTER);
        add(listBlock);

        addRemove();

    }

    // EFFECTS: places the budget summary
    private void addSummary() {
        summary = new JLabel("Total " + title + " received: " + 0, JLabel.CENTER);
        summary.setSize(WIDTH, HEIGHT / 4);
        this.add(summary);
    }

    // EFFECTS: updates the summary label
    public void updateSummary() {
        getController().getBudget().makeIncomeTotal();
        summary.setText("Total " + title + " received: " + getController().getBudget().getIncomeTotal());
    }

    // MODIFIES: this
    // EFFECTS: adds description, text box for remove, and entry button for removal
    private void addRemove() {
        JPanel remover = new JPanel(new GridLayout(1,3));
        add(remover);
        JLabel remove = new JLabel("Remove entry number:");
        remover.add(remove);
        JTextField box = new JTextField(5);
        remover.add(box);
        JButton removeButton = new JButton("Remove entry");
        remover.add(removeButton);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = Integer.parseInt(box.getText());
                    model.removeRow(row - 1);
                    getController().getBudget().getIncome().removeEntry(row - 1);
                    getController().getBudget().makeIncomeTotal();
                    updateSummary();
                    getController().getHomeTab().updateBudgetSummary();
                } catch (ArrayIndexOutOfBoundsException ex) {
                    // nothing happens
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Adds row to the table with new entry
    public void addRow(SingleEntry singleEntry) {
        model.addRow(new String[]{singleEntry.getName(),
                Double.toString(singleEntry.getAmount()),
                singleEntry.getDate()});
        updateSummary();
        getController().getHomeTab().updateBudgetSummary();
    }

    // MODIFIES: this
    // EFFECTS: reloads the table when retrieved
    public void reloadTable() {
        List<SingleEntry> entries = getController().getBudget().getIncomeEntries();
        for (SingleEntry s : entries) {
            addRow(s);
        }
    }

}