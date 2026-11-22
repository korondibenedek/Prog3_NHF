package rpn.szamologep.benedek.hazi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    private Operations operations;
    private static final double DELTA = 1e-9;

    @BeforeEach
    void setUp() {
        operations = new Operations();
    }

    @Test
    void testAdd() {
        assertEquals(7.5, operations.add(5.0, 2.5), DELTA);
        assertEquals(-2.0, operations.add(-5.0, 3.0), DELTA);
    }

    @Test
    void testSubtract() {
        assertEquals(8.0, operations.subtract(10.0, 2.0), DELTA);
        assertEquals(-8.0, operations.subtract(2.0, 10.0), DELTA);
    }

    @Test
    void testMultiply() {
        assertEquals(15.0, operations.multiply(3.0, 5.0), DELTA);
        assertEquals(0.0, operations.multiply(42.0, 0.0), DELTA);
    }

    @Test
    void testDivide() {
        assertEquals(5.0, operations.divide(10.0, 2.0), DELTA);
        assertThrows(ArithmeticException.class, () -> operations.divide(10.0, 0.0));
    }

    @Test
    void testPower() {
        assertEquals(8.0, operations.power(2.0, 3.0), DELTA);
        assertEquals(1.0, operations.power(5.0, 0.0), DELTA);
    }

    @Test
    void testSquareRoot() {
        assertEquals(4.0, operations.squareRoot(16.0), DELTA);
        assertEquals(0.0, operations.squareRoot(0.0), DELTA);
        assertThrows(IllegalArgumentException.class, () -> operations.squareRoot(-4.0));
    }

    @Test
    void testLogarithm() {
        assertEquals(1.0, operations.logarithm(Math.E), DELTA);
        assertThrows(IllegalArgumentException.class, () -> operations.logarithm(0.0));
        assertThrows(IllegalArgumentException.class, () -> operations.logarithm(-5.0));
    }

    @Test
    void testTrigonometricFunctions() {
        assertEquals(0.0, operations.sine(0.0), DELTA);
        assertEquals(1.0, operations.cosine(0.0), DELTA);
        assertEquals(0.0, operations.tangent(0.0), DELTA);
    }

    @Test
    void testFactorial() {
        assertEquals(1.0, operations.factorial(0.0), DELTA);
        assertEquals(120.0, operations.factorial(5.0), DELTA);
        assertThrows(IllegalArgumentException.class, () -> operations.factorial(-3.0));
        assertThrows(IllegalArgumentException.class, () -> operations.factorial(2.5));
    }

    @Test
    void testNegate() {
        assertEquals(-5.5, operations.negate(5.5), DELTA);
        assertEquals(5.5, operations.negate(-5.5), DELTA);
    }

    @Test
    void testMathRoot() {
        assertEquals(3.0, operations.mathRoot(27.0, 3.0), DELTA);
        assertThrows(ArithmeticException.class, () -> operations.mathRoot(9.0, 0.0));
        assertThrows(IllegalArgumentException.class, () -> operations.mathRoot(-16.0, 2.0));
    }

    @Test
    void testMathLog() {
        assertEquals(3.0, operations.mathLog(8.0, 2.0), DELTA);
        assertThrows(IllegalArgumentException.class, () -> operations.mathLog(-8.0, 2.0));
        assertThrows(IllegalArgumentException.class, () -> operations.mathLog(8.0, 1.0));
        assertThrows(IllegalArgumentException.class, () -> operations.mathLog(8.0, 0.0));
    }
}
