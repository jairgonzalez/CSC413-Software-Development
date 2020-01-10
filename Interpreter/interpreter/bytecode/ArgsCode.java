package interpreter.bytecode;
import interpreter.VirtualMachine;


import java.util.ArrayList;

public class ArgsCode extends ByteCode {
    private ArrayList<String> arguments;


    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        vm.newFrameAt(Integer.parseInt(arguments.get(0)));
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        return ("\n" + "ARGS" + " " + Integer.parseInt(arguments.get(0)));
    }


}
