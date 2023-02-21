package test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class FrequencyCount {
    public static void main(String... args) {
        System.out.println(frecuency("the input string"));
        System.out.println(frecuency("babdc"));
        System.out.println(frecuency(""));
        System.out.println(frecuency("kennethn"));
        System.out.println(frecuency("phqgh"));
    }

    public static String frecuency(String input) {
        StringBuilder resp = new StringBuilder();

        if (input.isEmpty()) {
            return "";
        }

        Arrays.stream( input.split(""))
                .collect(groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream().sorted(Map.Entry.comparingByKey())
                .forEachOrdered(e -> resp.append(e.getKey()).append(e.getValue()));

        return resp.toString();
    }
}
