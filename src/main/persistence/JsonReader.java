package persistence;

import model.SingleEntry;
import model.Budget;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// CITATION: readFile() and JsonReader() method cited from workshop example

// Represents a reader that reads JSON data from destination file
public class JsonReader {
    private String destinationFile;

    // EFFECTS: constructs reader to read JSON data from file
    public JsonReader(String location) {
        this.destinationFile = location;
    }

    // EFFECTS: reads budget from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Budget readBudget() throws IOException {
        String jsonData = readFile(destinationFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return analyzeBudget(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: analyzes budget from JSON object and returns it
    private Budget analyzeBudget(JSONObject jsonObject) {
        Budget budget = new Budget();
        addReports(budget, jsonObject);
        return budget;
    }

    // MODIFIES: budget
    // EFFECTS: retrieves reports from JSON object and adds them to budget
    private void addReports(Budget budget, JSONObject jsonObject) {
        JSONObject incomeReport = jsonObject.getJSONObject("income");
        JSONArray incomeEntries = incomeReport.getJSONArray("entries");
        for (Object json : incomeEntries) {
            JSONObject nextIncomeEntry = (JSONObject) json;
            addIncomeEntry(budget, nextIncomeEntry);
        }
        JSONObject expenseReport = jsonObject.getJSONObject("expense");
        JSONArray expenseEntries = expenseReport.getJSONArray("entries");
        for (Object json : expenseEntries) {
            JSONObject nextExpenseEntry = (JSONObject) json;
            addExpenseEntry(budget, nextExpenseEntry);
        }
    }

    // MODIFIES: budget
    // EFFECTS: retrieves entry from JSON object and adds it to income report
    private void addIncomeEntry(Budget budget, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double amount = jsonObject.getDouble("amount");
        String date = jsonObject.getString("date");
        SingleEntry incomeEntry = new SingleEntry(name, amount, date);
        budget.addIncomeEntry(incomeEntry);
    }

    // MODIFIES: budget
    // EFFECTS: retrieves entry from JSON object and adds it to expense report
    private void addExpenseEntry(Budget budget, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double amount = jsonObject.getDouble("amount");
        String date = jsonObject.getString("date");
        SingleEntry expenseEntry = new SingleEntry(name, amount, date);
        budget.addExpenseEntry(expenseEntry);
    }
}
