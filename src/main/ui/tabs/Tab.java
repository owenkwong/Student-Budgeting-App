package ui.tabs;

import ui.BudgetAppUI;

import javax.swing.*;
import java.awt.*;

// CITATION: button row idea from SmartHome

// abstract class for a tab
public abstract class Tab extends JPanel {

    private final BudgetAppUI controller;

    //REQUIRES: BudgetAppUI controller that holds this tab
    public Tab(BudgetAppUI controller) {
        this.controller = controller;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    //EFFECTS: returns the BudgetAppUI controller for this tab
    public BudgetAppUI getController() {
        return controller;
    }

}
