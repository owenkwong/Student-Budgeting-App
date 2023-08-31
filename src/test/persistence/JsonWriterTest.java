package persistence;

import model.Budget;
import model.SingleEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonWriterTest extends ConfirmEntry {

    @Test
    void testWriterNoFileFail() {
        try {
            Budget budget = new Budget();
            JsonWriter writer = new JsonWriter("./data/incorrectFile.json");
            writer.openWriter();
        } catch (IOException exception) {
            // pass test
        }
    }

    @Test
    void testWriterEmptyBudget() {
        try {
            Budget budget = new Budget();
            JsonWriter writer = new JsonWriter("./data/testEmptyBudgetWriter.json");
            writer.openWriter();
            writer.write(budget);
            writer.closeWriter();

            JsonReader reader = new JsonReader("./data/testEmptyBudgetWriter.json");
            budget = reader.readBudget();
            assertTrue(budget.getIncomeEntries().isEmpty());
            assertTrue(budget.getExpenseEntries().isEmpty());
        } catch (IOException exception) {
            fail("IOException should not be caught");
        }
    }

    @Test
    void testWriterNormalBudget() {
        try {
            Budget budget = new Budget();
            budget.addIncomeEntry(new SingleEntry("Wages",100, "2023/07/24"));
            budget.addIncomeEntry(new SingleEntry("Gift",50, "2023/07/23"));
            budget.addExpenseEntry(new SingleEntry("Food", 25, "2023/07/22"));
            budget.addExpenseEntry(new SingleEntry("Bills", 75, "2023/07/21"));
            JsonWriter writer = new JsonWriter("./data/testNormalBudgetWriter.json");
            writer.openWriter();
            writer.write(budget);
            writer.closeWriter();

            JsonReader reader = new JsonReader("./data/testNormalBudgetWriter.json");
            budget = reader.readBudget();
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
            fail("IOException should not be caught");
        }
    }
}
