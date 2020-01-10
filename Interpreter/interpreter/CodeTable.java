/**
 * Code table of byte codes in language X
 *
 * @key name of a specific byte code
 * @value name of the class that the key belongs to.
 * returns Class name as a string.
 */
package interpreter;

import java.util.HashMap;

public class CodeTable {

    private static HashMap<String, String> codeTable;

    private CodeTable() {
    }

    public static void init() {
        codeTable = new HashMap<>();
        codeTable.put("HALT", "HaltCode");
        codeTable.put("POP", "PopCode");
        codeTable.put("FALSEBRANCH", "FalseBranchCode");
        codeTable.put("GOTO", "GotoCode");
        codeTable.put("STORE", "StoreCode");
        codeTable.put("LOAD", "LoadCode");
        codeTable.put("LIT", "LitCode");
        codeTable.put("ARGS", "ArgsCode");
        codeTable.put("CALL", "CallCode");
        codeTable.put("RETURN", "ReturnCode");
        codeTable.put("BOP", "BopCode");
        codeTable.put("READ", "ReadCode");
        codeTable.put("WRITE", "WriteCode");
        codeTable.put("LABEL", "LabelCode");
        codeTable.put("DUMP", "DumpCode");
    }

    /**
     * A method to facilitate the retrieval of the names
     * of a specific byte code class.
     *
     * @param key for byte code.
     * @return class name of desired byte code.
     */
    public static String getClassName(String key) {

        return codeTable.get(key);

    }
}
