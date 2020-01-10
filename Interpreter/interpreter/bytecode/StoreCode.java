package interpreter.bytecode;

import interpreter.VirtualMachine;


import java.util.ArrayList;

public class StoreCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        vm.Store(Integer.parseInt(arguments.get(0)));
    }

    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        String return_str = "STORE" + " " + arguments.get(0);

        if (arguments.size() == 2) { //this is for the more complex dumping case, we append what we can here, then append the last value in the statement in the VM since its accessible there, and not to break encapsulation
            return_str += " " + arguments.get(1) + "    " + arguments.get(1) + " = ";
        }
        return return_str;
    }

}
