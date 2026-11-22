package rpn.szamologep.benedek.hazi.model;

public class Operations {
    
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a / b;
    }
    
    public double power(double a, double b) {
        return Math.pow(a, b);
    }

    public double squareRoot(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Square root is not defined for negative numbers.");
        }
        return Math.sqrt(a);
    }

    public double logarithm(double a) {
        if (a <= 0) {
            throw new IllegalArgumentException("Logarithm is not defined for non-positive numbers.");
        }
        return Math.log(a);
    }

    public double sine(double a) {
        return Math.sin(a);
    }

    public double cosine(double a) {
        return Math.cos(a);
    }

    public double tangent(double a) {
        return Math.tan(a);
    }

    public double factorial(double a) {
        if (a < 0 || a %1 != 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers or non-integers.");
        }
        double result = 1;
        for (int i = 1; i <= a; i++) {
            result *= i;
        }
        return result;
    }

    public double negate(double a) {
        return -a;
    }
    
    public double mathRoot(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Root with degree zero is not allowed.");
        }
        if (a < 0 && b % 2 == 0) {
            throw new IllegalArgumentException("Even root of a negative number is not defined.");
        }
        return Math.pow(a, 1.0 / b);
    }

    public double mathLog(double a, double b) {
        if (a <= 0 || b <= 0 || b == 1) {
            throw new IllegalArgumentException("Logarithm is not defined for non-positive numbers or base 1.");
        }
        return Math.log(a) / Math.log(b);
    }



}
