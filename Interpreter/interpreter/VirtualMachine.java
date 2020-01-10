package interpreter;

import java.util.Stack;

import interpreter.bytecode.*;


public class VirtualMachine {

    private RunTimeStack runStack;
    private Stack<Integer> returnAddrs;
    private Program program;
    private int pc;
    private Boolean isRunning;
    private boolean dump;

    protected VirtualMachine(Program program) {

        this.program = program;
    }

    public void halt() {
        isRunning = false;
    }

    void executeProgram() {

        pc = 0;
        int top_runstack = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<>();
        isRunning = true;
        try {


            while (isRunning) {
                ByteCode code = program.getCode(pc);
                if (code instanceof StoreCode && dump && ((StoreCode) code).getArguments().size() == 2) {
                    top_runstack = runStack.peek();
                }


                code.execute(this);

                if (dump) {
                    String temp_tostring = code.toString();

                    if (!(code instanceof StoreCode) && !(code instanceof WriteCode) && !(code instanceof ReturnCode && ((ReturnCode) code).getArguments().size() == 1) && !(code instanceof CallCode) && !(code instanceof DumpCode)) { //if this runs, we don't require special modifications for printing the dump statements
                        System.out.println(temp_tostring);
                    } else if (code instanceof StoreCode && ((StoreCode) code).getArguments().size() == 2) {
                        System.out.println(temp_tostring + top_runstack);
                    } else if (code instanceof ReturnCode) {       //ReturnCode size is 2
                        System.out.println(code.toString() + runStack.peek());
                    } else if (code instanceof WriteCode) {    //print WRITE then the top of the stack
                        System.out.println(temp_tostring);
                    } else if (code instanceof CallCode) {
                        int arr_arguments[] = this.getArgs();
                        temp_tostring += "(";
                        for (int i = 0; i < arr_arguments.length; i++) {
                            temp_tostring += arr_arguments[i];
                            temp_tostring += ",";
                        }
                        if (temp_tostring.charAt(temp_tostring.length() - 1) == ',') {
                            temp_tostring = temp_tostring.substring(0, temp_tostring.length() - 1);
                        }
                        temp_tostring += ")";

                        System.out.println(temp_tostring);
                    }

                    runStack.dump();    //dumping run stack
                }

                pc++;
            }
        } catch (Exception x) {
            System.out.println("Exception in vm.executeProgram");
        }
    }


    public void dumping_off() {          //turns dumping off
        dump = false;
    }

    public void dumping_on() {           //turns dumping on
        dump = true;
    }

    public void set_PC(int new_pc_val) {   //setter for PC
        pc = new_pc_val;
    }

    public int get_PC() {                  //getter for PC
        return pc;
    }

    public int popRunStack() {
        return runStack.pop();
    }

    public void mult_popRunStack(int num_to_pop) {   //pop multiple values off the run time stack
        runStack.multiple_pop(num_to_pop);
    }

    public int pop_returnAddrs() {
        return returnAddrs.pop();
    }

    public void push_returnAddrs(int new_returnAddrs) {
        returnAddrs.push(new_returnAddrs);
    }

    public void pushRunStack(int push_val) {
        runStack.push(push_val);
    }

    public int peekRunStack() {
        return runStack.peek();
    }

    public void newFrameAt(int offset_val) {
        runStack.newFrameAt(offset_val);
    }

    public void popFrame() {
        runStack.popFrame();
    }

    public int Store(int offset_val) {
        return runStack.store(offset_val);
    }

    public int Load(int offset) {
        return runStack.load(offset);
    }

    public int[] getArgs() { //helper function to return an array of the current runtime stack(this is used in printing the arguments in the dump statement for functions)
        return runStack.get_args();
    }


}
