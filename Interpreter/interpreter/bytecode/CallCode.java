package interpreter.bytecode;

import interpreter.VirtualMachine;


import java.util.ArrayList;

public class CallCode extends ByteCode {
    private ArrayList<String> arguments;
    private int destination_addr;

    public void init(ArrayList<String> args) {

        arguments = args;
    }

    public ArrayList<String> getArguments() {

        return arguments;
    }

    public void execute(VirtualMachine vm) {
        vm.push_returnAddrs(vm.get_PC());
        vm.set_PC(destination_addr - 1);
    }

    public void setDestination_addr(int dest) {
        destination_addr = dest;
    }

    public String toString() {
        String return_str = "CALL " + this.getArguments().get(0) + "        ";
        String arg0 = arguments.get(0);
        int base_id = arg0.indexOf("<<");
        for (int i = 0; i < base_id; i++) {
            return_str += arg0.charAt(i);
        }
        if (base_id == -1) {  //if there is no "<<" (ie. CALL f) then we assume the argument is the entire base id
            return_str += arguments.get(0);
        }


        return return_str;
    }
}
