package com.globant;

import java.util.Arrays;

/*
Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return the researcher's h-index.
*/
public class HIndex {
    public static void main(String[] args) {
        int[] input = new int[]{3,0,6,1,5};
        System.out.println(Arrays.toString(input).replaceAll("[\\[\\],]", ""));
        System.out.println(getIndex(input));
        System.out.println("-".repeat(12));

        input = new int[]{0,1};
        System.out.println(Arrays.toString(input).replaceAll("[\\[\\],]", ""));
        System.out.println(getIndex(input));
        System.out.println("-".repeat(12));

        input = new int[]{3,0,6,1,5,5,5,9,2,4};
        System.out.println(Arrays.toString(input).replaceAll("[\\[\\],]", ""));
        System.out.println(getIndex(input));
        System.out.println("-".repeat(12));
    }

    private static int getIndex(int[] input) {
        int pos = 0;

        Arrays.sort(input);

        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i] < pos + 1) {
                break;
            } else {
                pos++;
            }
        }

        return pos;
    }
}
/*
Tests

3 0 6 1 5
3
------------
0 1
1
------------
3 0 6 1 5 5 5 9 2 4
5
*/
