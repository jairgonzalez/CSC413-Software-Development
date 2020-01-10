package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator {

    @Override
    public int priority() {
        return 3; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Operand execute(Operand op1, Operand op2) {
        Operand Answer = new Operand(power(op1.getValue(), op2.getValue()));
        return Answer;
        //To change body of generated methods, choose Tools | Templates.
    }

    public int power(int i, int j) {
        int Answer = i;
        for (int count = 2; count <= j; count++) {
            Answer = Answer * i;
        }
        return Answer;
    }
}