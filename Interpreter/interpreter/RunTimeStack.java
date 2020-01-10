package interpreter;

import java.util.ArrayList;
import java.util.Stack;

public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>(); // creates the first entry for the frame pointer stack, for the main
        framePointer.add(0);
    }


    void dump() {

        String strstack = "";    //to hold the dumped runtime stack

        for (int i = 0; i < framePointer.size(); i++) {
            strstack += "[";
            if (i == framePointer.size() - 1) {        //Check if we have the last element
                for (int j = framePointer.peek(); j < (runTimeStack.size()); j++) { // checks entire frame
                    strstack += runTimeStack.get(j);  // adds elements
                    strstack += ",";                 // seperates with a comma
                }

            } else {
                int dist = framePointer.get(i + 1) - framePointer.get(i); //To know how far to go on the runtimestack( Finding how many element are in the frame)

                for (int m = framePointer.get(i); m < framePointer.get(i) + dist; m++) { //looping the current frame
                    strstack += runTimeStack.get(m); //inserting the current element in the runtime stack
                    strstack += ",";                 //add comma
                }

            }

            if (strstack.charAt(strstack.length() - 1) == ',') {  //cheking the last element = a comma
                strstack = strstack.substring(0, strstack.length() - 1);  //decrement the size of the string by 1

            }
            strstack += "] ";    // closing brace with a space at the end of the outer-loop iteration
        }

        System.out.println(strstack);

    }


    int peek() {
        return runTimeStack.get(runTimeStack.size() - 1);
    }

    int pop() {
        int size_currframe = runTimeStack.size() - framePointer.peek();
        int top_stack = 0;
        if (size_currframe >= 1) {  //check if the frame have enough space to pop
            top_stack = runTimeStack.get(runTimeStack.size() - 1);
            runTimeStack.remove(runTimeStack.size() - 1);
        }
        return top_stack;
    }

    void multiple_pop(int number_to_pop) {  //helper function to pop multiple elements from the run time stack
        for (int i = 0; i < number_to_pop; i++) {
            this.pop();
        }
    }

    int push(int push_val) {
        runTimeStack.add(push_val);
        return push_val;
    }


    void newFrameAt(int offset) { //offset is the number of frames to go down from the top of the run time stack to assign a new frame
        framePointer.push(runTimeStack.size() - offset);
    }

    void popFrame() {    //this is to removes the top frame of the run time stack
        int number_to_pop = runTimeStack.size() - framePointer.peek(); //get the number of elements in current frame
        int return_val = runTimeStack.get(runTimeStack.size() - 1);

        this.multiple_pop(number_to_pop);  //clear the frame
        this.framePointer.pop();
        runTimeStack.add(return_val); //add the return value onto the run time stack
    }

    int store(int offset) {  //this offset is at the bottom of the run time stack frame
        int value_to_store = runTimeStack.get(runTimeStack.size() - 1);

        runTimeStack.remove(runTimeStack.size() - 1); //pop top of stack
        runTimeStack.set(offset + framePointer.peek(), value_to_store); //replace element at proper index

        return value_to_store;
    }

    int load(int offset) {    //offset from the start of the frame
        int load_val = runTimeStack.get(framePointer.peek()) + offset;

        runTimeStack.add(load_val);
        return load_val;
    }

    Integer push(Integer val) {
        runTimeStack.add(val);
        return val;
    }

    int[] get_args() {        //A helper function to get an array holding the current arguments)
        final int number_args = (runTimeStack.size() - framePointer.peek());
        int[] arr = new int[number_args];

        for (int i = framePointer.peek(); i < runTimeStack.size(); i++) { //go from top frame on run time stack
            arr[i - framePointer.peek()] = runTimeStack.get(i); //assign each element in the array
        }
        return arr;
    }
}