package persistence;

import model.SingleEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConfirmEntry {
    protected void confirmEntry(String name, double amount, String date, SingleEntry singleEntry) {
        assertEquals(name, singleEntry.getName());
        assertEquals(amount, singleEntry.getAmount());
        assertEquals(date, singleEntry.getDate());
    }
}
