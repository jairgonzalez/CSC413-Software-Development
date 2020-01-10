package edu.csc413.calculator.evaluator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class EvaluatorDriver {
    /**
     * Driver class that uses Evaluator to evaluate expressions.
     * The expressions can either be given as a command line argument
     * , typed in at the keyboard or you can used "auto" as a command line
     * argument to run predefined expressions. If you wish to type in expressions
     * give NO command line arguments. Otherwise you may give a list of
     * strings as a command line argument and the driver will run all strings
     * in that list.
     *
     * @param args command line arguments.
     */

    static HashMap<String, String> testExpressions;

    /*
     * **** Static Initializer Block Explanation ****
     * This block of code is called a static initializer. It is executed when
     * this class loaded  by the JVM. When the class is actually loaded is tricky.
     * But since this class has a main method and we can use it as our main class,
     * it will be loaded immediately. However, if it wasn't our main class, it
     * would be loaded when the class is referenced.
     */

    static {
        EvaluatorDriver.testExpressions = new HashMap<>();
        EvaluatorDriver.testExpressions.put("1+2", "3");
        EvaluatorDriver.testExpressions.put("1/2", "0");
        EvaluatorDriver.testExpressions.put("1+2*3", "7");
        EvaluatorDriver.testExpressions.put("(1+2)*3", "9");
        EvaluatorDriver.testExpressions.put("2-(3/10)+2-5", "-1");
        EvaluatorDriver.testExpressions.put("(6-12*2)/3", "-6");
        EvaluatorDriver.testExpressions.put("3^2", "9");
        EvaluatorDriver.testExpressions.put("3^2/2", "4");
        EvaluatorDriver.testExpressions.put("3^2/2 +(4+5)", "13");
        EvaluatorDriver.testExpressions.put("3^2 + (2^4) +(4+5)", "34");
        EvaluatorDriver.testExpressions.put("3+2-9+8*2 + (3+9-8/2)", "20");
        EvaluatorDriver.testExpressions.put("2+3-5*((2-3)*2-5*2+3*(2-3-5-5*6)+4/2)*2-9", "1176");
    }

    public static void main(String... args) {
        BufferedReader input;
        String exp;
        int res;

        Evaluator ev = new Evaluator();
        if (args.length == 0) {
            /* if no command line arguments are given, we will ask for expressions */
            try {
                input = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    System.out.print("Enter an Expression: ");
                    exp = input.readLine();
                    res = ev.eval(exp);
                    System.out.printf("Expression : %s , Result %6d\n", exp, res);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else if (args.length == 1 && (args[0].toLowerCase()).equals("auto")) {
            /* if only 1 command line argument is given AND it is auto we will
             * run expressions in the above HashMap
             */
            EvaluatorDriver.testExpressions.forEach((expression, expectedResult) -> {
                try {
                    String yourResult = ev.eval(expression) + "";
                    System.out.printf("%45s %5s : %5s : %7s%n", expression,
                            expectedResult,
                            yourResult,
                            expectedResult.equals(yourResult) ? "Passed" : "Failed");
                }catch(Exception ex){
                    System.out.printf("%45s %5s : %5s : %7s%n", expression,
                            expectedResult,
                            "failed",
                            expectedResult.equals("") ? "Passed" : "Failed");
                }

            });
        } else {
            /* if a list of expressions are given as command line arguments
             * we will execute all of them and display results.
             * The format of the list of expressions should be:
             * exp0 exp1 exp2 ... expN
             * Each expressions should separated by spaces.
             */
            for (String ex : args) {
                res = ev.eval(ex);
                System.out.printf("Expression : %s , Result: %-6d\n", ex, res);
            }

        }
    }
}
