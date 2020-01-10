package interpreter.bytecode;

import interpreter.VirtualMachine;


import java.util.ArrayList;
import java.util.Scanner;

public class ReadCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter an integer: ");
        while (!in.hasNextInt()) { //once we get an int, we break out of this, so we read it
            System.out.print("Please enter a integer: ");
            in.next();
        }
        int val = in.nextInt();
        vm.pushRunStack(val);
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return "READ";
    }


}