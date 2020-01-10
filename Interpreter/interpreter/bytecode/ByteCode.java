package interpreter.bytecode;

import java.util.ArrayList;

import interpreter.VirtualMachine;

public abstract class ByteCode {

    public abstract void init(ArrayList<String> args);  //ByteCode initialization function

    public abstract void execute(VirtualMachine vm);    //ByteCode execution function

    public abstract String toString();                  //toString is used in dumping

}
