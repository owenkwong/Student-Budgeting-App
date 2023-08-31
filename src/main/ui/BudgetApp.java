package ui;

import model.Budget;
import model.SingleEntry;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// CITATION: forcing into loops to begin user input and processes modelled after teller example

// Student budgeting application
public class BudgetApp {
    private static final String STORAGE_FILE = "./data/budget.json";
    private Budget budget;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the budgeting application
    public BudgetApp() throws FileNotFoundException {
        runBudgeter();
    }

    // MODIFIES: this
    // EFFECTS: takes in user input and processes
    private void runBudgeter() {
        boolean status = true;
        String key = null;

        starter();

        while (status) {
            displayOptions();
            key = input.next();
            key = key.toLowerCase();

            if (key.equals("q")) {
                status = false;
            } else {
                followKey(key);
            }
        }
        System.out.println("Application closed");
    }

    // MODIFIES: this
    // EFFECTS: initializes income and expense reports with no entries
    private void starter() {
        budget = new Budget();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(STORAGE_FILE);
        jsonReader = new JsonReader(STORAGE_FILE);
    }

    // EFFECTS: shows a menu to the user of possible keys to progress
    private void displayOptions() {
        // displays the overall total (income-expense)
        System.out.println("\nTotal budget remaining: $" + budget.getOverallTotal());
        System.out.println("Choose one of:");
        System.out.println("i -> income");
        System.out.println("e -> expenses");
        System.out.println("s -> save budget reports to file");
        System.out.println("r -> retrieve budget reports from file");
        System.out.println("q -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes the key that the user inputs to open either income or expense reports
    // or save/retrieve from file
    private void followKey(String key) {
        if (key.equals("i")) {
            openIncome();
        } else if (key.equals("e")) {
            openExpenses();
        } else if (key.equals("s")) {
            saveBudget();
        } else if (key.equals("r")) {
            retrieveBudget();
        } else {
            System.out.println("Not a valid key");
        }
    }

    // EFFECTS: start to display the income report
    private void openIncome() {
        String choice = ""; // go into option select

        System.out.println("\n");
        System.out.println("Total income received: $" + budget.getIncomeTotal()); // displays total income
        for (SingleEntry i : budget.getIncomeEntries()) {
            System.out.println(i.getName() + "---" + "$" + (i.getAmount()) + "---" + i.getDate());
        }

        while (!(choice.equals("a") || choice.equals("b"))) {
            System.out.println("Select:");
            System.out.println("a to add an income entry");
            System.out.println("b to go back");
            choice = input.next();
            choice = choice.toLowerCase();
        }

        if (choice.equals("a")) {
            addIncome();
        } else {
            System.out.println("Returning back to home screen");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds entry to income report with name, amount, and date
    private void addIncome() {
        System.out.print("Name:");
        String name = input.next();
        System.out.print("Amount(+ve):$");
        double amount = input.nextDouble();
        System.out.print("Date(YYYY/MM/DD):");
        String date = input.next();
        budget.addIncomeEntry(new SingleEntry(name,amount,date));
        budget.updateOverallTotal();
    }

    // EFFECTS: start to display the expense report
    private void openExpenses() {
        String choice = ""; // go into option select

        System.out.println("\n");
        System.out.println("Total expenses paid: $" + budget.getExpenseTotal()); // displays total expenses paid
        for (SingleEntry i : budget.getExpenseEntries()) {
            System.out.println(i.getName() + "---" + "$" + (i.getAmount()) + "---" + i.getDate());
        }

        while (!(choice.equals("a") || choice.equals("b"))) {
            System.out.println("Select:");
            System.out.println("a to add an expense entry");
            System.out.println("b to go back");
            choice = input.next();
            choice = choice.toLowerCase();
        }

        if (choice.equals("a")) {
            addExpense();
        } else {
            System.out.println("Returning back to home screen");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds entry to expense report with name, amount, and date
    private void addExpense() {
        System.out.print("Name:");
        String name = input.next();
        System.out.print("Amount(+ve):$");
        double amount = input.nextDouble();
        System.out.print("Date(YYYY/MM/DD):");
        String date = input.next();
        budget.addExpenseEntry(new SingleEntry(name,amount,date));
        budget.updateOverallTotal();
    }

    // EFFECTS: saves the budget to file
    private void saveBudget() {
        try {
            jsonWriter.openWriter();
            jsonWriter.write(budget);
            jsonWriter.closeWriter();
            System.out.println("Saved your budget to " + STORAGE_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save your budget to " + STORAGE_FILE);
        }
    }

    // MODIFIES: this
    // EFFECTS: retrieves budget from file
    private void retrieveBudget() {
        try {
            budget = jsonReader.readBudget();
            budget.updateOverallTotal();
            System.out.println("Retrieved your budget from file " + STORAGE_FILE);
        } catch (IOException e) {
            System.out.println("Unable to retrieve your budget from " + STORAGE_FILE);
        }
    }
}




