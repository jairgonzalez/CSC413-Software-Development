import edu.csc413.calculator.operators.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperatorTester {

    @Test
    void testCheck(){
        assertTrue(Operator.check("+"));
        assertTrue(Operator.check("*"));
        assertTrue(Operator.check("^"));
        assertTrue(Operator.check("-"));
        assertTrue(Operator.check("/"));
        assertFalse(Operator.check("156"));
        assertFalse(Operator.check("156.0"));
        assertFalse(Operator.check("x"));
        assertFalse(Operator.check("**"));
    }

    @Test
    void testGetOperator(){
        Operator op = Operator.getOperator("+");
        assertTrue(op instanceof AdditionOperator);
        op = Operator.getOperator("-");
        assertTrue(op instanceof SubtractionOperator);
        op = Operator.getOperator("/");
        assertTrue(op instanceof DivisionOperator);
        op = Operator.getOperator("*");
        assertTrue(op instanceof MultiplicationOperator);
        op = Operator.getOperator("^");
        assertTrue(op instanceof PowerOperator);
        op = Operator.getOperator("c");
        assertTrue(op == null);
    }
}
