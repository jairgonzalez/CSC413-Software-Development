package edu.csc413.calculator.evaluator;
import edu.csc413.calculator.operators.Operator;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
    private final Stack<Operand> operandStack;
    private final Stack<Operator> operatorStack;

    private StringTokenizer tokenizer;
    private static final String DELIMITERS = "+-*^/#!() ";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public int eval(String expression) {
        String token;
        // # marker denotes begining of expression
        // used to determine if stack is empty w/o
        // throwing emptyStackExpression while acting
        // as a stop marker when evaluating expression
        Operator init = Operator.getOperator("#");
        operatorStack.push(init);


        this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);

        while (this.tokenizer.hasMoreTokens()) {
            // filter out spaces
            if (!(token = this.tokenizer.nextToken()).equals(" ")) {
                // check if token is an operand
                if (Operand.check(token)) {
                    operandStack.push(new Operand(token));
                } else {
                    if (!Operator.check(token)) {
                        System.out.println("*****invalid token******");
                        System.exit(1);
                    }

                    Operator newOperator = Operator.getOperator(token);

                    if (operatorStack.peek() == Operator.getOperator("#")) {
                        operatorStack.push(newOperator);

                    } else if (newOperator == Operator.getOperator("(")) {
                        operatorStack.push(newOperator);

                    } else if (newOperator == Operator.getOperator(")")) {
                        while (operatorStack.peek() != Operator.getOperator("(")) {
                            execute(operatorStack.pop());
                        }
                        operatorStack.pop();
                        // lastly check priority, i.e *,/ > +,-
                    } else {
                        while (operatorStack.peek().priority() >= newOperator.priority()) {
                            execute(operatorStack.pop());
                        }
                        // if operator makes it this far, push to stack
                        operatorStack.push(newOperator);
                    }
                }
            }
        }

        while (operatorStack.peek() != Operator.getOperator("#")) {
            execute(operatorStack.pop());
        }
        return operandStack.pop().getValue();
    }


    private void execute(Operator oldOpr) {
        Operand op2 = operandStack.pop();
        Operand op1 = operandStack.pop();
        operandStack.push(oldOpr.execute(op1, op2));
    }
}
