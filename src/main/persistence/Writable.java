package persistence;

import org.json.JSONObject;

// CITATION: writable modelled after workshop example which allows classes to be written to JSON

public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
