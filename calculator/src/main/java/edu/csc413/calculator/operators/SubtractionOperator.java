package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;
public class SubtractionOperator extends Operator {

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        Operand Answer = new Operand(op1.getValue()- op2.getValue());
        return Answer
                ;
    }

}
