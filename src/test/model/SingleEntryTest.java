package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SingleEntryTest {
    private SingleEntry testEntry;

    @BeforeEach
    void runBefore() {
        testEntry = new SingleEntry("Wages", 100, "2023/07/15");
    }

    @Test
    void testConstructor() {
        assertEquals("Wages", testEntry.getName());
        assertEquals(100, testEntry.getAmount());
        assertEquals("2023/07/15", testEntry.getDate());
    }
}
