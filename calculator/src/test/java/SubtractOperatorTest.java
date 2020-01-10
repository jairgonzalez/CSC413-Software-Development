import edu.csc413.calculator.evaluator.Operand;
import edu.csc413.calculator.operators.SubtractionOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Subtraction Test")
public class SubtractOperatorTest {

    @Test
    public void subtractionTest01(){
        Operand op1 =  new Operand(6);
        Operand op2 =  new Operand(11);
        SubtractionOperator so =  new SubtractionOperator();
        Operand res = new Operand(so.execute(op1,op2).getValue());
        assertEquals(-5, res.getValue());
    }

    @Test
    public void subtractionTest02(){
        Operand op1 =  new Operand(11);
        Operand op2 =  new Operand(6);
        SubtractionOperator so =  new SubtractionOperator();
        Operand res = new Operand(so.execute(op1,op2).getValue());
        assertEquals(5, res.getValue());
    }

    @Test
    public void subtractionTest03(){
        Operand op1 =  new Operand(-11);
        Operand op2 =  new Operand(6);
        SubtractionOperator so =  new SubtractionOperator();
        Operand res = new Operand(so.execute(op1,op2).getValue());
        assertEquals(-17, res.getValue());
    }

    @Test
    public void subtractionTest04(){
        Operand op1 =  new Operand(11);
        Operand op2 =  new Operand(-6);
        SubtractionOperator so =  new SubtractionOperator();
        Operand res = new Operand(so.execute(op1,op2).getValue());
        assertEquals(17, res.getValue());
    }

    @Test
    public void subtractionPriorityTest(){
        assertEquals(2, (new SubtractionOperator().priority()));
    }
}