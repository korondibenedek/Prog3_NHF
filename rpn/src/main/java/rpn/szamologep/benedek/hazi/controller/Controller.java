package rpn.szamologep.benedek.hazi.controller;
import java.awt.event.ActionListener;

import rpn.szamologep.benedek.hazi.model.MemoryManager;
import rpn.szamologep.benedek.hazi.model.RpnEngine;
import rpn.szamologep.benedek.hazi.view.CalculatorView;
import javax.swing.AbstractButton;
import java.util.EmptyStackException;

public class Controller implements ActionListener {
    private final RpnEngine engine;
    private final MemoryManager memoryManager;
    private final CalculatorView view;

    public Controller(RpnEngine engine, MemoryManager memoryManager, CalculatorView view) {
        this.engine = engine;
        this.memoryManager = memoryManager;
        this.view = view;
        view.addActionListener(this);
    }

    @Override 
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String command = e.getActionCommand();
        try{
            switch (command) {
                case "Enter":
                    double value = Double.parseDouble(view.getInputText());
                    engine.push(value);
                    view.clearInputText();
                    break;

                case "Clear":
                    engine.clear();
                    break;

                case "Drop":
                    engine.drop();
                    break;

                case "Swap":
                    engine.swap();
                    break;

                case "+/-":
                    engine.negate();
                    break;

                case "CE":
                    view.clearInputText();
                    break;

                case "MS":
                    memoryManager.saveToFile(engine.peek());
                    break;

                case "MR":
                    double memoryValue = memoryManager.loadFromFile();
                    engine.push(memoryValue);
                    break;

                case "Exit":
                    System.exit(0);
                    break;

                case "0":
                    view.setInputText(view.getInputText() + "0");
                    break;

                case "1":
                    view.setInputText(view.getInputText() + "1");
                    break;

                case "2":
                    view.setInputText(view.getInputText() + "2");
                    break;

                case "3":
                    view.setInputText(view.getInputText() + "3");
                    break;

                case "4":
                    view.setInputText(view.getInputText() + "4");
                    break;

                case "5":
                    view.setInputText(view.getInputText() + "5");
                    break;

                case "6":
                    view.setInputText(view.getInputText() + "6");
                    break;

                case "7":
                    view.setInputText(view.getInputText() + "7");
                    break;

                case "8":
                    view.setInputText(view.getInputText() + "8");
                    break;

                case "9":
                    view.setInputText(view.getInputText() + "9");
                    break;

                case "+":
                    engine.performOperation("+");
                    break;

                case "-":
                    engine.performOperation("-");
                    break;

                case "*":
                    engine.performOperation("*");
                    break;

                case "/":
                    engine.performOperation("/");
                    break;

                case "^":
                    engine.performOperation("^");
                    break;
                case "sin":
                    engine.performOperation("sin");
                    break;

                case "cos":
                    engine.performOperation("cos");
                    break;

                case "tan":
                    engine.performOperation("tan");
                    break;

                case "!":
                    engine.performOperation("!");
                    break;

                case "√x": 
                    engine.performOperation("sqrt"); 
                    break;

                case "log":
                    engine.performOperation("log");
                    break;

                case"ⁿ√x":
                    engine.performOperation("mathRoot"); 
                    break;

                case "logₓ(y)":
                    engine.performOperation("mathLog"); 
                    break;

                case ".":
                    view.setInputText(view.getInputText() + command);
                    break;  
                case "Help":
                    view.showInfo("RPN Calculator Help:\n\n" +
                    "1. Enter numbers and operations in Reverse Polish Notation (RPN).\n" +
                    "2. Use the 'Enter' button to push numbers onto the stack.\n" +
                    "3. Use the operation buttons (+, -, *, /, ^, sin, cos, tan, !, √x, log, ⁿ√x, logₓ(y)) to perform calculations.\n" +
                    "4. Use 'MS' to save the top of the stack to memory and 'MR' to recall it.\n" +
                    "5. Use 'Clear' to clear the stack, 'Drop' to remove the top element, 'Swap' to swap the top two elements, and '+/-' to negate the top element.\n" );
                    break;

                default:    
                    engine.performOperation(command);
            }
            view.refreshTable();
        } catch (EmptyStackException ex) {
            view.showError("Error: Not enough elements in the stack for this operation.");
        }catch(NumberFormatException ex){
            // Handle invalid number format
            view.showError("Error: Invalid number format.");
        }catch (Exception ex) {
            // Handle other exceptions
            view.showError("Error: " + ex.getMessage());
        }
        
        
    }

}
