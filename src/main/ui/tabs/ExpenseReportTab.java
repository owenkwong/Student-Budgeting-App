package ui.tabs;

import model.SingleEntry;
import ui.BudgetAppUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// This is the tab that displays the summary for expenses
public class ExpenseReportTab extends Tab {
    private JScrollPane pane;
    private String title;
    private JLabel summary;
    private DefaultTableModel model;

    // EFFECTS: constructs an expense report tab to display expense report stats
    public ExpenseReportTab(BudgetAppUI controller, String name) {
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

    // EFFECTS: adds description, text box, and entry button for removal of row
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
                    getController().getBudget().getExpense().removeEntry(row - 1);
                    getController().getBudget().makeExpenseTotal();
                    updateSummary();
                    getController().getHomeTab().updateBudgetSummary();
                } catch (ArrayIndexOutOfBoundsException ex) {
                    // nothing happens
                }
            }
        });
    }

    // EFFECTS: places the budget summary
    private void addSummary() {
        summary = new JLabel("Total " + title + " received: " + 0, JLabel.CENTER);
        summary.setSize(WIDTH, HEIGHT / 4);
        this.add(summary);
    }

    // EFFECTS: updates the summary label
    public void updateSummary() {
        getController().getBudget().makeExpenseTotal();
        summary.setText("Total " + title + " received: " + getController().getBudget().getExpenseTotal());
    }

    // MODIFIES: this
    // EFFECTS: adds a row to the expense report
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
        List<SingleEntry> entries = getController().getBudget().getExpenseEntries();
        for (SingleEntry s : entries) {
            addRow(s);
        }
    }

}