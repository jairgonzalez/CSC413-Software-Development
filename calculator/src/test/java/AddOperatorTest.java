import edu.csc413.calculator.evaluator.Operand;
import edu.csc413.calculator.operators.AdditionOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Addition Test")
public class AddOperatorTest {

    @Test
    public void additionTest01(){
        Operand op1 =  new Operand(6);
        Operand op2 =  new Operand(11);
        AdditionOperator ap =  new AdditionOperator();
        Operand res = new Operand(ap.execute(op1,op2).getValue());
        assertEquals(17, res.getValue());
    }

    @Test
    public void additionTest02(){
        Operand op1 =  new Operand(11);
        Operand op2 =  new Operand(6);
        AdditionOperator ap =  new AdditionOperator();
        Operand res = new Operand(ap.execute(op1,op2).getValue());
        assertEquals(17, res.getValue());
    }

    @Test
    public void additionTest03(){
        Operand op1 =  new Operand(6);
        Operand op2 =  new Operand(-11);
        AdditionOperator ap =  new AdditionOperator();
        Operand res = new Operand(ap.execute(op1,op2).getValue());
        assertEquals(-5, res.getValue());
    }

    @Test
    public void additionTest04(){
        Operand op1 =  new Operand(-11);
        Operand op2 =  new Operand(6);
        AdditionOperator ap =  new AdditionOperator();
        Operand res = new Operand(ap.execute(op1,op2).getValue());
        assertEquals(-5, res.getValue());
    }
//Could change to 2 to to pass
    @Test
    public void additionPriorityTest(){
        assertEquals(1, (new AdditionOperator().priority()));
    }
}