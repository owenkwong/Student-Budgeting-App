package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BudgetTest {
    private Budget testBudget;
    private SingleEntry entry1;
    private SingleEntry entry2;
    private SingleEntry entry3;

    @BeforeEach
    void runBefore() {
        testBudget = new Budget();
        entry1 = new SingleEntry("A", 100, "2004");
        entry2 = new SingleEntry("B", 50, "2005");
        entry3 = new SingleEntry("C", 25, "2006");
    }

    @Test
    void testConstructor() {
        assertTrue(testBudget.getIncomeEntries().isEmpty());
        assertTrue(testBudget.getExpenseEntries().isEmpty());
        assertTrue(testBudget.getIncome().getListEntry().isEmpty());
        assertTrue(testBudget.getExpense().getListEntry().isEmpty());
    }

    @Test
    void testMakeIncomeTotalNone() {
        testBudget.makeIncomeTotal();
        assertEquals(0, testBudget.getIncomeTotal());
    }

    @Test
    void testMakeIncomeTotalSome() {
        testBudget.addIncomeEntry(entry1);
        testBudget.addIncomeEntry(entry2);
        testBudget.addIncomeEntry(entry3);
        testBudget.makeIncomeTotal();
        assertEquals(175, testBudget.getIncomeTotal());
    }

    @Test
    void testMakeExpenseTotalNone() {
        testBudget.makeExpenseTotal();
        assertEquals(0, testBudget.getExpenseTotal());
    }

    @Test
    void testMakeExpenseTotalSome() {
        testBudget.addExpenseEntry(entry1);
        testBudget.addExpenseEntry(entry2);
        testBudget.addExpenseEntry(entry3);
        testBudget.makeExpenseTotal();
        assertEquals(175, testBudget.getExpenseTotal());
    }

    @Test
    void testUpdateOverallTotalNone() {
        testBudget.updateOverallTotal();
        assertEquals(0, testBudget.getOverallTotal());
    }

    @Test
    void testUpdateOverallTotalSome() {
        testBudget.addIncomeEntry(entry1);
        testBudget.addExpenseEntry(entry2);
        testBudget.addExpenseEntry(entry3);
        testBudget.updateOverallTotal();
        assertEquals(25, testBudget.getOverallTotal());
    }

    @Test
    void testAddIncomeEntry() {
        assertTrue(testBudget.getIncomeEntries().isEmpty());
        testBudget.addIncomeEntry(entry1);
        assertFalse(testBudget.getIncomeEntries().isEmpty());
    }

    @Test
    void testAddExpenseEntry() {
        assertTrue(testBudget.getExpenseEntries().isEmpty());
        testBudget.addExpenseEntry(entry1);
        assertFalse(testBudget.getExpenseEntries().isEmpty());
    }
}
