package interpreter.bytecode;
import interpreter.VirtualMachine;


import java.util.ArrayList;

public class PopCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        vm.mult_popRunStack(Integer.parseInt(arguments.get(0)));
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return ("POP" + " " + arguments.get(0));
    }

}
