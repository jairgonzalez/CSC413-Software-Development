import edu.csc413.calculator.evaluator.Operand;
import edu.csc413.calculator.operators.PowerOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Power Test")
public class PowerOperatorTest {

    @Test
    public void PowerTest01(){
        Operand op1 =  new Operand(2);
        Operand op2 =  new Operand(3);
        PowerOperator po =  new PowerOperator();
        Operand res = new Operand(po.execute(op1,op2).getValue());
        assertEquals(8, res.getValue());
    }

    @Test
    public void Powertest02(){
        Operand op1 =  new Operand(-2);
        Operand op2 =  new Operand(8);
        PowerOperator po =  new PowerOperator();
        Operand res = new Operand(po.execute(op1,op2).getValue());
        assertEquals(256, res.getValue());
    }

    @Test
    public void Powertest03(){
        Operand op1 =  new Operand(2);
        Operand op2 =  new Operand(5);
        PowerOperator po =  new PowerOperator();
        Operand res = new Operand(po.execute(op1,op2).getValue());
        assertEquals(32, res.getValue());
    }

    @Test
    public void Powertest04(){
        Operand op1 =  new Operand(-3);
        Operand op2 =  new Operand(4);
        PowerOperator po =  new PowerOperator();
        Operand res = new Operand(po.execute(op1,op2).getValue());
        assertEquals(81, res.getValue());
    }

    @Test
    public void Powertest05(){
        Operand op1 =  new Operand(-3);
        Operand op2 =  new Operand(5);
        PowerOperator po =  new PowerOperator();
        Operand res = new Operand(po.execute(op1,op2).getValue());
        assertEquals(-243, res.getValue());
    }

    @Test
    public void powerPriorityTest(){
        assertEquals(3, (new PowerOperator().priority()));
    }
}
