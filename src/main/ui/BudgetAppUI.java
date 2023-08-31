package ui;

import model.Budget;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.HomeTab;
import ui.tabs.IncomeReportTab;
import ui.tabs.ExpenseReportTab;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

// CITATION: display style takes inspiration from SmartHome

// user interface of the BudgetApp using java swing
public class BudgetAppUI extends JFrame {
    public static final int HOME_TAB_INDEX = 0;
    public static final int INCOME_TAB_INDEX = 1;
    public static final int EXPENSE_TAB_INDEX = 2;
    public static final int INCOME_EDIT_TAB_INDEX = 3;
    public static final int EXPENSE_EDIT_TAB_INDEX = 4;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private JTabbedPane sidebar;
    private Budget budget;
    private IncomeReportTab incomeTab;
    private ExpenseReportTab expenseTab;
    private HomeTab homeTab;
    private JPanel incomeEditTab;
    private JPanel expenseEditTab;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String STORAGE_FILE = "./data/budgetGUI.json";


    // EFFECTS: starts the appliciation
    public static void main(String[] args) {
        try {
            new BudgetAppUI();
        } catch (FileNotFoundException e) {
            System.out.println("Was not able to start application since no file was found");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates BudgetAppUI, creates empty Budget, and loads tabs
    private BudgetAppUI() throws FileNotFoundException {
        super("BudgetApp");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog events = EventLog.getInstance();
                for (Event next : events) {
                    System.out.println(next.getDescription());
                }
            }
        });

        budget = new Budget();
        jsonWriter = new JsonWriter(STORAGE_FILE);
        jsonReader = new JsonReader(STORAGE_FILE);
        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);
        loadTabs();
        add(sidebar);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: adds home tab, income (report) tab and expense (report) tab to this UI
    private void loadTabs() {
        homeTab = new HomeTab(this);
        incomeTab = new IncomeReportTab(this, "Income");
        expenseTab = new ExpenseReportTab(this, "Expenses");
        incomeEditTab = new IncomeAdder(this);
        expenseEditTab = new ExpenseAdder(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(incomeTab, INCOME_TAB_INDEX);
        sidebar.setTitleAt(INCOME_TAB_INDEX, "Income");
        sidebar.add(expenseTab, EXPENSE_TAB_INDEX);
        sidebar.setTitleAt(EXPENSE_TAB_INDEX, "Expenses");
        sidebar.add(incomeEditTab, INCOME_EDIT_TAB_INDEX);
        sidebar.setTitleAt(INCOME_EDIT_TAB_INDEX, "Income Edit");
        sidebar.add(expenseEditTab, EXPENSE_EDIT_TAB_INDEX);
        sidebar.setTitleAt(EXPENSE_EDIT_TAB_INDEX, "Expense Edit");

    }

    // EFFECTS: saves the budget to file
    public void saveBudget() {
        try {
            jsonWriter.openWriter();
            jsonWriter.write(budget);
            jsonWriter.closeWriter();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save your budget to " + STORAGE_FILE);
        }
    }

    // MODIFIES: this
    // EFFECTS: retrieves budget from file
    public void retrieveBudget() {
        try {
            budget = jsonReader.readBudget();
            budget.updateOverallTotal();
            getHomeTab().updateBudgetSummary();
            getIncomeTab().updateSummary();
            getExpenseTab().updateSummary();
            getIncomeTab().reloadTable();
            getExpenseTab().reloadTable();
        } catch (IOException e) {
            System.out.println("Unable to retrieve your budget from " + STORAGE_FILE);
        }
    }


    public Budget getBudget() {
        return budget;
    }

    public IncomeReportTab getIncomeTab() {
        return incomeTab;
    }

    public ExpenseReportTab getExpenseTab() {
        return expenseTab;
    }

    public HomeTab getHomeTab() {
        return homeTab;
    }
}
