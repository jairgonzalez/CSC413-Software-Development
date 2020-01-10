package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class BopCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        String operation = arguments.get(0);
        int first_operand = 0;
        int second_operand = 0;

        second_operand = vm.popRunStack();
        first_operand = vm.popRunStack();
        switch (operation) {
            case "+":      //replace with switch later
                vm.pushRunStack(first_operand + second_operand);
                break;
            case "-":
                vm.pushRunStack(first_operand - second_operand);
                break;
            case "/":
                vm.pushRunStack(first_operand / second_operand);
                break;
            case "*":
                vm.pushRunStack(first_operand * second_operand);
                break;
            case "==":
                int is_equal;
                if (first_operand == second_operand) {
                    is_equal = 1;
                } else {
                    is_equal = 0;
                }
                vm.pushRunStack(is_equal);
                break;
            case "!=":
                int is_not_equal;
                if (first_operand != second_operand) {
                    is_not_equal = 1;
                } else {
                    is_not_equal = 0;
                }
                vm.pushRunStack(is_not_equal);
                break;
            case "<=":
                int is_less_equal;
                if (first_operand <= second_operand) {
                    is_less_equal = 1;
                } else {
                    is_less_equal = 0;
                }
                vm.pushRunStack(is_less_equal);
                break;
            case ">=":
                int is_greater_equal;
                if (first_operand >= second_operand) {
                    is_greater_equal = 1;
                } else {
                    is_greater_equal = 0;
                }
                vm.pushRunStack(is_greater_equal);
                break;
            case "<":
                int is_less_than;
                if (first_operand < second_operand) {
                    is_less_than = 1;
                } else {
                    is_less_than = 0;
                }
                vm.pushRunStack(is_less_than);
                break;
            case ">":
                int is_greater;
                if (first_operand > second_operand) {
                    is_greater = 1;
                } else {
                    is_greater = 0;
                }
                vm.pushRunStack(is_greater);
                break;
            case "&":
                int and;
                if ((first_operand == 1) && (second_operand == 1)) {
                    and = 1;
                } else {
                    and = 0;
                }
                vm.pushRunStack(and);
                break;
            case "|":
                int or;
                if ((first_operand == 1) || (second_operand == 1)) {
                    or = 1;
                } else {
                    or = 0;
                }
                vm.pushRunStack(or);
                break;

        }
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return ("BOP " + arguments.get(0));
    }


}