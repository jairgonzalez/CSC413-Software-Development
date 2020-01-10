package interpreter.bytecode;

import interpreter.VirtualMachine;


import java.util.ArrayList;

public class LitCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        vm.pushRunStack(Integer.parseInt(arguments.get(0)));
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        String str_to_return = "LIT " + arguments.get(0);
        if (arguments.size() == 2) {
            str_to_return += " " + arguments.get(1) + "   int " + arguments.get(1);
        }
        return str_to_return;
    }

}
