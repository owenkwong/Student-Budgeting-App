package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReportTest {
    private Report testReport;

    @BeforeEach
    void runBefore() {
        testReport = new Report();
    }

    @Test
    void testConstructor() {
        // makes sure ArrayList is empty
        assertTrue(testReport.getListEntry().isEmpty());
    }

    @Test
    void testAddEntry() {
        // make sure that addEntry adds an entry and the ArrayList is no longer empty
        testReport.addEntry(new SingleEntry("Food", 100,  "2004/07/28"));
        assertFalse(testReport.getListEntry().isEmpty());
        testReport.addEntry(new SingleEntry("Wages", 110,  "2005/07/28"));
        assertFalse(testReport.getListEntry().isEmpty());
    }

    @Test
    void testMakeTotal() {
        // tests that make total reruns and gets the total each time
        testReport.addEntry(new SingleEntry("Food", 100,  "2004/07/28"));
        testReport.makeTotal();
        assertEquals(100.0, testReport.getTotal());
        testReport.addEntry(new SingleEntry("Wages", 110,  "2005/07/28"));
        testReport.makeTotal();
        assertEquals(210.0, testReport.getTotal());
        testReport.addEntry(new SingleEntry("Wages", 210,  "2005/07/28"));
        testReport.makeTotal();
        assertEquals(420.0, testReport.getTotal());
    }

    @Test
    void testRemoveEntry() {
        testReport.addEntry(new SingleEntry("Food", 100,  "2004/07/28"));
        testReport.addEntry(new SingleEntry("Wages", 110,  "2005/07/28"));
        testReport.removeEntry(0);
        testReport.removeEntry(0);
        assertTrue(testReport.getListEntry().isEmpty());
    }

}
