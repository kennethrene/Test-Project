package test;

import java.util.Arrays;

public class VariableNames {
    private static final String C_CHARACTER = "_";

    public static void main(String... args) {
        System.out.println(modifyVariableNames("variable_name_or_number_2"));
        System.out.println(modifyVariableNames("modify_variableName"));
        System.out.println(modifyVariableNames("_modify"));
        System.out.println(modifyVariableNames("_modify_"));
        System.out.println(modifyVariableNames("_"));
        System.out.println(modifyVariableNames("_2oModify_3_6"));
        System.out.println(modifyVariableNames(""));
        System.out.println(modifyVariableNames("VariableName"));
        System.out.println(modifyVariableNames("thisIsAVariable"));
        System.out.println(modifyVariableNames("VariableNamE"));
        System.out.println(modifyVariableNames("V"));
    }

    public static String modifyVariableNames(String input) {
        StringBuilder inputTemp = new StringBuilder(input);
        StringBuilder resp = new StringBuilder();

        if (inputTemp.indexOf(C_CHARACTER) != -1) {
            int pos = inputTemp.indexOf(C_CHARACTER);

            if (pos == 0) {
                inputTemp.delete(0, 1);
                pos = inputTemp.indexOf(C_CHARACTER);
            }

            while (pos != -1 && pos != inputTemp.length() - 1) {
                resp.append(inputTemp.substring(0, pos));
                resp.append(inputTemp.substring(pos + 1, pos + 2).toUpperCase());
                inputTemp.delete(0, pos + 2);
                pos = inputTemp.indexOf(C_CHARACTER);
            }

            resp.append(inputTemp.substring(0, inputTemp.length()));
        } else {
            Arrays.stream(input.split("")).forEach(c -> {
                if (c.matches("[A-Z]") && resp.length() > 0) {
                    resp.append(C_CHARACTER).append(c.toLowerCase());
                } else {
                    resp.append(c.toLowerCase());
                }
            });
        }

        return resp.toString();
    }
}
