package rpn.szamologep.benedek.hazi.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.GridLayout;



public class CalculatorView extends JFrame{
    private final StackTableModel stackTableModel;
    private final JTextField inputField = new JTextField();
    private final List<AbstractButton> allbuttons = new ArrayList<>();

    public CalculatorView(StackTableModel stackTableModel) {
        this.stackTableModel = stackTableModel;
        setTitle("RPN Calculator");
        setSize(400, 600);
        setLayout(new BorderLayout(5,5));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        createMenuBar();
        createTableField();
        createBottomPanel();
    }  

    public void createMenuBar() {
        // Create a menu bar with File, Edit, and Help menus
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        // Add menu items to the File menu
        JMenuItem saveItem = new JMenuItem("MS - Save");
        fileMenu.add(saveItem);
        JMenuItem loaditem = new JMenuItem("MR - Load");
        fileMenu.add(loaditem);
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);

        // Add menu items to the Edit menu
        JMenuItem clearItem = new JMenuItem("Clear");
        editMenu.add(clearItem);
        JMenuItem swapItem = new JMenuItem("Swap");
        editMenu.add(swapItem);
        JMenuItem dropItem = new JMenuItem("Drop");
        editMenu.add(dropItem);

        // Add menu items to the Help menu
        JMenuItem helpItem = new JMenuItem("Help");
        helpMenu.add(helpItem);

        saveItem.setActionCommand("MS");
        loaditem.setActionCommand("MR");
        allbuttons.addAll(List.of(saveItem, loaditem, exitItem, clearItem, swapItem,dropItem, helpItem));
    }

    public void createTableField() {
        JTable table= new JTable(stackTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout(5,5));
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setActionCommand("Enter");
        bottomPanel.add(inputField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 4, 4, 4));

        String[] buttonLabels = {
            "sin", "cos", "tan", "!",
            "√x", "log", "^", "ⁿ√x",
            "logₓ(y)", "MS", "MR", "Clear",
            "Drop", "Swap", "+/-", "CE",
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "Enter", "+"
        };

        for (String label : buttonLabels) {
            AbstractButton button = new javax.swing.JButton(label);
            allbuttons.add(button);
            buttonPanel.add(button);
        }

        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void addActionListener(ActionListener listener) {
        for (AbstractButton button : allbuttons) {
            button.addActionListener(listener);
        }
        inputField.addActionListener(listener);
    }

    public String getInputText() {
        return inputField.getText();
    }

    public void setInputText(String text) {
        inputField.setText(text);
    }

    public void clearInputText() {
        inputField.setText("");
    }

    public void refreshTable() {
        stackTableModel.refresh();
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showInfo(String message) {
       JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }   
}
