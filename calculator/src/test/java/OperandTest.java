import edu.csc413.calculator.evaluator.Operand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Operand Test")
public class OperandTest {

    @Test
    public void getValueTest(){
        Operand op = new Operand(6);
        assertEquals(6,op.getValue());
    }

    @Test
    public void getValueTypeTest(){
        Operand op = new Operand(6);

        assertTrue(isInt(op.getValue()));
    }

    @Test
    public void checkValueTest(){
        assertTrue(Operand.check("13"));
        assertTrue(Operand.check("19"));
        assertTrue(Operand.check("465465"));
        assertFalse(Operand.check("c"));
        assertFalse(Operand.check("3.0"));
        assertFalse(Operand.check("3."));
        assertFalse(Operand.check("343324fd"));
    }

    private boolean isInt(Object o){
        return o instanceof Integer;
    }


}
