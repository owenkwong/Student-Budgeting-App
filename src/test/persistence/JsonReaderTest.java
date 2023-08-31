package persistence;

import model.Budget;
import model.SingleEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends ConfirmEntry {

    @Test
    void testReaderNoFileFail() {
        JsonReader reader = new JsonReader(".data/incorrectFile.json");
        try {
            Budget budget = reader.readBudget();
            fail("Supposed to give IOException");
        } catch (IOException exception) {
            // pass test
        }
    }

    @Test
    void testReaderEmptyBudget() {
        JsonReader reader = new JsonReader("./data/testEmptyBudgetReader.json");
        try {
            Budget budget = reader.readBudget();
            assertTrue(budget.getIncomeEntries().isEmpty());
            assertTrue(budget.getExpenseEntries().isEmpty());
        } catch (IOException exception) {
            fail("Failed to read from file");
        }
    }

    @Test
    void testReaderNormalBudget() {
        JsonReader reader = new JsonReader("./data/testNormalBudgetReader.json");
        try {
            Budget budget = reader.readBudget();
            // these retrieve respective entries
            ArrayList<SingleEntry> incomeEntries = budget.getIncomeEntries();
            ArrayList<SingleEntry> expenseEntries = budget.getExpenseEntries();
            // checks size of entries
            assertEquals(2, incomeEntries.size());
            assertEquals(2, expenseEntries.size());
            // checks income entries
            confirmEntry("Wages",100, "2023/07/24", incomeEntries.get(0));
            confirmEntry("Gift",50, "2023/07/23", incomeEntries.get(1));
            // checks expense entries
            confirmEntry("Food", 25, "2023/07/22", expenseEntries.get(0));
            confirmEntry("Bills", 75, "2023/07/21", expenseEntries.get(1));
        } catch (IOException exception) {
            fail("Failed to read from file");
        }
    }


}
