
package interpreter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

import interpreter.bytecode.ByteCode;


public class ByteCodeLoader {

    private BufferedReader byteSource;

    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN LOADCODES.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }

    /**
     * This function reads one line of source code at a time.
     * For each line it :
     * Tokenizes the string to break it into parts.
     * Grabs THE correct class name for the given ByteCode from CodeTable
     * Creates an instance of the ByteCode class name returned from code table.
     * Parses any additional arguments for the given ByteCode and sends them to
     * the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {

        Program program = new Program();
        String line;
        try {
            while ((line = byteSource.readLine()) != null) {
                StringTokenizer stoken = new StringTokenizer(line); //A string tokenizer to operate on the line
                String bytecode_name = stoken.nextToken(" ");   //getting the bytecode
                String bytecode_class_name = CodeTable.getClassName(bytecode_name);   //getting the class name from the CodeTable
                Class cls = Class.forName("interpreter.bytecode." + bytecode_class_name);
                ByteCode bcode = (ByteCode) cls.getDeclaredConstructor().newInstance();  //instance of the proper subclass
                ArrayList<String> args = new ArrayList<>();
                while (stoken.hasMoreTokens()) {
                    args.add(stoken.nextToken());
                }
                bcode.init(args);  //passing in args to the init function
                program.addCode(bcode);   //adding the fully initialized code
            }

        } catch (Exception ex) {    //if an exception occurs, we catch it.
            //
        }

        program.resolveAddrs(); //all addresses be resolved

        return program;
    }


}