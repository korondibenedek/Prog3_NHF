package rpn.szamologep.benedek.hazi;
import rpn.szamologep.benedek.hazi.controller.Controller;
import rpn.szamologep.benedek.hazi.model.MemoryManager;
import rpn.szamologep.benedek.hazi.model.RpnEngine;
import rpn.szamologep.benedek.hazi.view.CalculatorView;
import rpn.szamologep.benedek.hazi.view.StackTableModel;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            RpnEngine engine = new RpnEngine();
            MemoryManager memoryManager = new MemoryManager();
            StackTableModel tableModel = new StackTableModel(engine);
            CalculatorView view = new CalculatorView(tableModel);
            new Controller(engine, memoryManager, view);
            view.setVisible(true);
        });
    }
}