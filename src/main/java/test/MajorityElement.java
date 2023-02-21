package test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class MajorityElement {
    public static void main(String... args) {

        System.out.println(majority(3, new int[]{1, 2, 1}));
        System.out.println(majority(3, new int[]{1, 2, 3}));
        System.out.println(majority(4, new int[]{1, 2, 1, 2}));
    }

    public static int majority(int input1, int[] input2) {
        Map.Entry<Integer, Long> resp = Arrays.stream(input2).boxed()
                .collect(groupingBy(v -> v, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).get();

        if (resp.getValue() > input1 / 2) {
            return resp.getKey();
        } else {
            return -1;
        }
    }
}
