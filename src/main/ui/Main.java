package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new BudgetApp();
        } catch (FileNotFoundException exception) {
            System.out.println("Was not able to start application since no file was found");
        }
    }
}
