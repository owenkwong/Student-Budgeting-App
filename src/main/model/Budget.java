package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// CITATION: toJson() method modeled similar to workshop example

// budget class that holds income and expense reports
public class Budget implements Writable {
    private Report income;
    private Report expense;
    private double incomeTotal;
    private double expenseTotal;
    private double overallTotal;

    // EFFECTS: creates a budget with empty income and expense reports
    public Budget() {
        income = new Report();
        expense = new Report();
    }

    // MODIFIES: this
    // EFFECTS: updates the total income
    public void makeIncomeTotal() {
        income.makeTotal();
        incomeTotal = income.getTotal();
    }

    // MODIFIES: this
    // EFFECTS: updates the total expense
    public void makeExpenseTotal() {
        expense.makeTotal();
        expenseTotal = expense.getTotal();
    }

    // MODIFIES: this
    // EFFECTS: updates the overall total
    public void updateOverallTotal() {
        this.overallTotal = 0;
        makeIncomeTotal();
        makeExpenseTotal();
        overallTotal = incomeTotal - expenseTotal;
    }

    // MODIFIES: this
    // EFFECTS: adds an entry to income
    public void addIncomeEntry(SingleEntry singleEntry) {
        income.addEntry(singleEntry);
        EventLog.getInstance().logEvent(new Event("An income entry has been added."));
    }

    // MODIFIES: this
    // EFFECTS: adds an entry to expense
    public void addExpenseEntry(SingleEntry singleEntry) {
        expense.addEntry(singleEntry);
        EventLog.getInstance().logEvent(new Event("An expense entry has been added."));
    }

    public ArrayList<SingleEntry> getIncomeEntries() {
        return income.getListEntry();
    }

    public ArrayList<SingleEntry> getExpenseEntries() {
        return expense.getListEntry();
    }

    public double getOverallTotal() {
        return overallTotal;
    }

    public double getIncomeTotal() {
        return incomeTotal;
    }

    public double getExpenseTotal() {
        return expenseTotal;
    }

    public Report getIncome() {
        return income;
    }

    public Report getExpense() {
        return expense;
    }

    @Override
    // Described in superclass
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("income", income.toJson());
        json.put("expense", expense.toJson());
        return json;
    }
}
