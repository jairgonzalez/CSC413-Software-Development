package interpreter.bytecode;

import interpreter.VirtualMachine;


import java.util.ArrayList;

public class DumpCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        if (arguments.get(0).equals("ON")) {
            vm.dumping_on();
        } else {
            vm.dumping_off();
        }
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return ("DUMP " + arguments.get(0));
    }
}
