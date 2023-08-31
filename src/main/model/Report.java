package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// CITATION: toJson() method modeled similar to workshop example

// income/expense report holds a list of entries of that type
public class Report implements Writable {
    private ArrayList<SingleEntry> listEntry;
    private double total;

    // EFFECTS: creates a report with no entries
    public Report() {
        listEntry = new ArrayList<SingleEntry>();
    }

    // MODIFIES: this
    // EFFECTS: adds the amounts of each entry in listEntry
    public void makeTotal() {
        this.total = 0;

        for (SingleEntry i : listEntry) {
            this.total = this.total  + i.getAmount();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds new single entry to listEntry
    public void addEntry(SingleEntry singleEntry) {
        this.listEntry.add(singleEntry);
    }

    // MODIFIES: this
    // EFFECTS: removes given index entry
    public void removeEntry(int index) {
        this.listEntry.remove(index);
        EventLog.getInstance().logEvent(new Event("A single entry has been removed"));
    }

    public ArrayList<SingleEntry> getListEntry() {
        return this.listEntry;
    }

    public double getTotal() {
        return this.total;
    }

    @Override
    // Described in superclass
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("entries", entriesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray entriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (SingleEntry s : listEntry) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}

