package test;

import util.FileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.stream.IntStream;

public class BalancedBrackets {

    private static boolean isOpenBracket(String bracket) {
        return bracket.equals("{") || bracket.equals("(") || bracket.equals("[");
    }

    private static boolean isRightCloseBracket(String openBracket, String closeBracket) {
        switch (openBracket) {
            case "{": return closeBracket.equals("}");
            case "[": return closeBracket.equals("]");
            case "(": return closeBracket.equals(")");
            default: return false;
        }
    }

    public static String isBalanced(String s) {
        Stack<String> stack = new Stack<>();
        String[] array = s.split("");

        for(String bracket: array) {
            if (stack.size() > 0) {
                if (isOpenBracket(bracket)) {
                    stack.push(bracket);
                } else if (!isRightCloseBracket(stack.pop(), bracket)) {
                    return "NO";
                }
            } else {
                if (isOpenBracket(bracket)) {
                    stack.push(bracket);
                } else {
                    return "NO";
                }
            }
        }

        return stack.size() == 0 ? "YES" : "NO";
    }


    public static void main(String[] args) throws IOException {
        FileUtil fileUtil = new FileUtil("balancedBrackets.txt");
        InputStream inputStream = fileUtil.getFileFromResourceAsStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();
                String result = BalancedBrackets.isBalanced(s);

                System.out.println(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
