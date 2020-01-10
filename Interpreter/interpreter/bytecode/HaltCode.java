package interpreter.bytecode;

import interpreter.VirtualMachine;


import java.util.ArrayList;

public class HaltCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        vm.halt();
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return "HALT";
    }

}
