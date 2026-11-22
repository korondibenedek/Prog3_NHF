package rpn.szamologep.benedek.hazi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class RpnEngineTest {

    private RpnEngine engine;
    private static final double DELTA = 1e-9;

    @BeforeEach
    void setUp() {
        engine = new RpnEngine();
    }

    @Test
    void testPushPeekPopAndSize() {
        engine.push(3.5);
        engine.push(7.0);

        assertEquals(2, engine.getSize());
        assertEquals(3.5, engine.getElementAt(0), DELTA);
        assertEquals(7.0, engine.getElementAt(1), DELTA);
        assertEquals(7.0, engine.peek(), DELTA);
        assertEquals(7.0, engine.pop(), DELTA);
        assertEquals(1, engine.getSize());
    }

    @Test
    void testBasicArithmeticOperationsAndOrder() {
        engine.push(10.0);
        engine.push(2.0);
        engine.performOperation("-");
        assertEquals(8.0, engine.peek(), DELTA);

        engine.push(4.0);
        engine.performOperation("/");
        assertEquals(2.0, engine.peek(), DELTA);

        engine.push(5.0);
        engine.performOperation("+");
        assertEquals(7.0, engine.peek(), DELTA);

        engine.push(3.0);
        engine.performOperation("*");
        assertEquals(21.0, engine.peek(), DELTA);
    }

    @Test
    void testDivisionByZeroPreservesStack() {
        engine.push(10.0);
        engine.push(0.0);

        assertThrows(ArithmeticException.class, () -> engine.performOperation("/"));

        assertEquals(2, engine.getSize());
        assertEquals(10.0, engine.getElementAt(0), DELTA);
        assertEquals(0.0, engine.getElementAt(1), DELTA);
    }

    @Test
    void testScientificOperations() {
        engine.push(2.0);
        engine.push(3.0);
        engine.performOperation("^");
        assertEquals(8.0, engine.peek(), DELTA);

        engine.push(16.0);
        engine.performOperation("sqrt");
        assertEquals(4.0, engine.peek(), DELTA);

        engine.performOperation("!");
        assertEquals(24.0, engine.peek(), DELTA);

        engine.clear();
        engine.push(27.0);
        engine.push(3.0);
        engine.performOperation("mathRoot");
        assertEquals(3.0, engine.peek(), DELTA);

        engine.clear();
        engine.push(8.0);
        engine.push(2.0);
        engine.performOperation("mathLog");
        assertEquals(3.0, engine.peek(), DELTA);
    }

    @Test
    void testDropAndClear() {
        engine.push(1.0);
        engine.push(2.0);
        engine.drop();

        assertEquals(1, engine.getSize());
        assertEquals(1.0, engine.peek(), DELTA);

        engine.clear();
        assertEquals(0, engine.getSize());
        assertThrows(EmptyStackException.class, () -> engine.drop());
    }

    @Test
    void testSwap() {
        engine.push(10.0);
        engine.push(20.0);
        engine.swap();

        assertEquals(20.0, engine.getElementAt(0), DELTA);
        assertEquals(10.0, engine.getElementAt(1), DELTA);
    }

    @Test
    void testNegate() {
        engine.push(42.0);
        engine.negate();
        assertEquals(-42.0, engine.peek(), DELTA);

        engine.clear();
        assertThrows(EmptyStackException.class, () -> engine.negate());
    }

    @Test
    void testInsufficientElementsAndInvalidOperation() {
        engine.push(5.0);

        assertThrows(EmptyStackException.class, () -> engine.performOperation("+"));
        assertEquals(1, engine.getSize());
        assertEquals(5.0, engine.peek(), DELTA);

        assertThrows(EmptyStackException.class, () -> engine.swap());
        assertThrows(UnsupportedOperationException.class, () -> engine.performOperation("unknown"));
        assertThrows(IndexOutOfBoundsException.class, () -> engine.getElementAt(5));
    }
}
