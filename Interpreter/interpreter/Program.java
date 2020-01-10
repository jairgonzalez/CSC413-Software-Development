package interpreter;

import java.util.ArrayList;

import interpreter.bytecode.*;

import java.util.HashMap;

public class Program {

    private ArrayList<ByteCode> program;

    public Program() {

        program = new ArrayList<>();

    }

    protected ByteCode getCode(int pc) {

        return this.program.get(pc);

    }

    public int getSize() {

        return this.program.size();
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-stucture bytecodes are stored in.
     *
     * @param 'program' Program object that holds a list of ByteCodes
     */
    public void resolveAddrs() {

        HashMap<String, Integer> labelkey_to_linenum = new HashMap<>();  //Hash map stores the labels keys to the line numbers

        for (int i = 0; i < this.program.size(); i++) {   //for each iteration, get one element from the array list of initialized bytecodes
            ByteCode bc = program.get(i);   //get the 'i' th bytecode from the array list of bytecodes
            if (bc instanceof LabelCode) {    //checking the bytecode an instance of LabelCode class.
                labelkey_to_linenum.put(((LabelCode) bc).getArguments().get(0), i);  //Put the value in hash map with first argument as key, and line number as value
            }
        }

        for (int i = 0; i < this.program.size(); i++) {
            ByteCode bcode = program.get(i);
            if ((bcode instanceof FalseBranchCode)) {
                int lval = labelkey_to_linenum.get(((FalseBranchCode) bcode).getArguments().get(0));  //line value for the FalseBranchCode's address
                ((FalseBranchCode) bcode).setDestination_addr(lval);
            } else if (bcode instanceof GotoCode) {
                int lval = labelkey_to_linenum.get(((GotoCode) bcode).getArguments().get(0));        //line value for the GotoCode's address
                ((GotoCode) bcode).setDestination_addr(lval);
            } else if (bcode instanceof CallCode) {
                int lval = labelkey_to_linenum.get(((CallCode) bcode).getArguments().get(0));        //line value for the CallCode's address
                ((CallCode) bcode).setDestination_addr(lval);
            }

        }


    }

    protected void addCode(ByteCode bc_post_initialization) {    //this helper function allows us to add fully INITILIAZED bytecodes to the Array List
        program.add(bc_post_initialization);
    }

}