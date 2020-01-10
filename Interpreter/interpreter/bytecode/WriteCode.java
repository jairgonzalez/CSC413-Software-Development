package interpreter.bytecode;

import interpreter.VirtualMachine;


import java.util.ArrayList;


public class WriteCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        System.out.println(vm.peekRunStack());
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return "WRITE";
    }


}
