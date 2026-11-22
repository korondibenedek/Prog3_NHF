package rpn.szamologep.benedek.hazi.model;
import java.util.Stack;
import java.util.EmptyStackException;


public class RpnEngine {
    private Stack<Double> stack;
    private Operations operations;

    public RpnEngine() {
        this.stack = new Stack<>();
        this.operations = new Operations();
    }

    public void push(double value) {
        stack.push(value);
    }

    public double pop() {
        return stack.pop();
    }

    public double peek() {
        return stack.peek();
    }

    public void performOperation(String operation) {
        switch (operation) {
            case "+":
                if (haslessThenTwoElements()) throw new EmptyStackException();
                stack.push(operations.add(stack.pop(), stack.pop()));
                break;

            case "-":
                if (haslessThenTwoElements()) throw new EmptyStackException();
                double subtrahend = stack.pop();
                double minuend = stack.pop();
                stack.push(operations.subtract(minuend, subtrahend));
                break;

            case "*":
                if (haslessThenTwoElements()) throw new EmptyStackException();
                stack.push(operations.multiply(stack.pop(), stack.pop()));
                break;

            case "/":
                if (haslessThenTwoElements()) throw new EmptyStackException();
                double divisor = stack.pop();
                double dividend = stack.pop();
                try {
                    stack.push(operations.divide(dividend, divisor));
                } catch (ArithmeticException e) {
                    stack.push(dividend); // Push back the dividend to maintain stack state
                    stack.push(divisor);  // Push back the divisor to maintain stack state
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                break;

            case "^":
                if (haslessThenTwoElements()) throw new EmptyStackException();
                double exponent = stack.pop();
                double base = stack.pop();
                stack.push(operations.power(base, exponent));
                break;

            case "sqrt":
                if (stack.isEmpty()) throw new EmptyStackException();
                stack.push(operations.squareRoot(stack.pop()));
                break;

            case "log":
                if (stack.isEmpty()) throw new EmptyStackException();
                stack.push(operations.logarithm(stack.pop()));
                break;

            case "sin":
                if (stack.isEmpty()) throw new EmptyStackException();
                stack.push(operations.sine(stack.pop()));
                break;

            case "cos":
                if (stack.isEmpty()) throw new EmptyStackException();
                stack.push(operations.cosine(stack.pop()));
                break;

            case "tan":
                if (stack.isEmpty()) throw new EmptyStackException();
                stack.push(operations.tangent(stack.pop()));
                break;

            case "!":
                if (stack.isEmpty()) throw new EmptyStackException();
                stack.push(operations.factorial(stack.pop()));
                break;

            case "mathRoot":
                if (haslessThenTwoElements()) throw new EmptyStackException();
                double rootDegree = stack.pop();
                double rootValue = stack.pop();
                stack.push(operations.mathRoot(rootValue, rootDegree));
                break;

            case "mathLog":
                if (haslessThenTwoElements()) throw new EmptyStackException();
                double logBase = stack.pop();
                double logValue = stack.pop();
                stack.push(operations.mathLog(logValue, logBase));
                break;

            default:
                throw new UnsupportedOperationException("Operation not supported: " + operation);
        }
    }

    public boolean haslessThenTwoElements() {
        return stack.size() < 2;
    }

    public void drop() {
        if (!stack.isEmpty()) {
            stack.pop();
        } else {
            throw new EmptyStackException();
        }
    }

    public void clear() {
        stack.clear();
    }

    public void swap() {
        if (stack.size() < 2) {
            throw new EmptyStackException();
        }
        double first = stack.pop();
        double second = stack.pop();
        stack.push(first);
        stack.push(second);
    }

    public void negate() {
        if (!stack.isEmpty()) {
            double value = stack.pop();
            stack.push(-value);
        } else {
            throw new EmptyStackException();
        }
    }

    public int getSize() {
        return stack.size();
    }

    public double getElementAt(int index) {
        if (index < 0 || index >= stack.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return stack.get(index);
    }
    
}
