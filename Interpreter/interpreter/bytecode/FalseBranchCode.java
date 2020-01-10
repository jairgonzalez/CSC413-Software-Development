package interpreter.bytecode;

import interpreter.VirtualMachine;

import java.util.ArrayList;

public class FalseBranchCode extends ByteCode {
    private ArrayList<String> arguments;
    private int destination_addr;
    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        int bool_val = vm.popRunStack();
        if (bool_val == 0) {
            vm.set_PC(destination_addr - 1);
        }
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }


    public void setDestination_addr(int dest) {
        destination_addr = dest;
    }

    public String toString() {
        return ("FALSEBRANCH " + arguments.get(0));
    }


}
