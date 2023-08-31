package model;

import org.json.JSONObject;
import persistence.Writable;

// CITATION: toJson() method modeled similar to workshop example

// A single entry with name, amount (CAD), and date of entry
public class SingleEntry implements Writable {
    private String name;
    private double amount;
    private String date;

    // EFFECTS: constructs an entry with given name, date, and amount
    public SingleEntry(String name, double amount, String date) {
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    @Override
    // Described in superclass
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("amount", amount);
        json.put("date", date);
        return json;
    }
}

