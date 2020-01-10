package interpreter.bytecode;

import interpreter.VirtualMachine;


import java.util.ArrayList;

public class ReturnCode extends ByteCode {
    private ArrayList<String> arguments;

    public void init(ArrayList<String> args) {
        arguments = args;
    }

    public void execute(VirtualMachine vm) {
        vm.popFrame();
        int return_addr = vm.pop_returnAddrs();
        vm.set_PC(return_addr);
    }


    public ArrayList<String> getArguments() {
        return arguments;
    }

    public String toString() {
        String return_str = "RETURN";

        if (arguments.size() == 1) {
            return_str += " " + arguments.get(0) + "    exit ";
            String arg0 = arguments.get(0);
            int base_id = arg0.indexOf("<<"); //looking for this symbol, to find where the base-id ends
            for (int i = 0; i < base_id; i++) { //looping through the string for the base_id
                return_str += arg0.charAt(i); //
            }
            if (base_id == -1) {  //if there is no "<<" (ie. RETURN f) then we assume the argument is the entire base-id
                return_str += arguments.get(0); //appending the base-id for the case where it isn't of the <<>> form
            }
            return_str += ":"; //in the VM, we will append/print the actual return value since we can't access it here
        }


        return return_str;
    }
}