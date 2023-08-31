package persistence;

import model.Budget;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// CITATION: writer modelled after workshop example

// Represents a writer that writes budget as JSON to destination file
public class JsonWriter {
    private static final int SPACING = 2;
    private PrintWriter writer;
    private String destinationFile;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destinationFile) {
        this.destinationFile = destinationFile;
    }

    // MODIFIES: this
    // EFFECTS: opens the writer but throws FileNotFoundException if writer cannot be opened to write in
    public void openWriter() throws FileNotFoundException {
        writer = new PrintWriter(new File(destinationFile));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of full budget to file
    public void write(Budget budget) {
        JSONObject json = budget.toJson();
        saveInFile(json.toString(SPACING));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void closeWriter() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: saves the string into json file
    private void saveInFile(String json) {
        writer.print(json);
    }

}
