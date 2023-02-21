package test;

import util.FileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MinMaxRiddle {
    static long[] riddle(long[] arr) {
        long[] resp = new long[arr.length];
        PriorityQueue<Long> qAsc = new PriorityQueue<>();
        PriorityQueue<Long> qDes = new PriorityQueue<>(Collections.reverseOrder());
        int pos = 0;

        List<Long> numbers = Arrays.stream(arr).boxed().collect(Collectors.toList());

        qDes.addAll(numbers);
        qAsc.addAll(numbers);
        resp[pos++] = qDes.peek();
        resp[arr.length - 1] = qAsc.peek();

        for (int g=2; g<arr.length; g++) {
            int finalG = g;
            resp[pos++] = IntStream.range(1, arr.length + 2 - finalG).mapToObj(index -> numbers.subList(index - 1, (index - 1) + finalG))
                    .map(s -> {
                        qAsc.clear();
                        qAsc.addAll(s);
                        return qAsc.peek();
                    })
                    .max(Long::compare).get();
        }

        return resp;
    }

    static long[] riddle2(long[] arr) {
        long[] resp = new long[arr.length];
        PriorityQueue<Long> qAsc = new PriorityQueue<>();
        PriorityQueue<Long> qDes = new PriorityQueue<>(Collections.reverseOrder());
        int pos = 0;

        List<Long> numbers = Arrays.stream(arr).boxed().collect(Collectors.toList());

        qDes.addAll(numbers);
        qAsc.addAll(numbers);
        resp[pos++] = qDes.peek();
        resp[arr.length - 1] = qAsc.peek();

        for (int g=2; g<arr.length; g++) {
            int finalG = g;
            resp[pos++] = IntStream.range(1, arr.length + 2 - finalG)
                    .mapToObj(index -> {
                        PriorityQueue<Long> q = new PriorityQueue();
                        q.addAll(numbers.subList(index - 1, (index - 1) + finalG));
                        return q;
                    })
                    .map(s -> s.peek())
                    .max(Long::compare).get();
        }

        return resp;
    }

    public static void main(String[] args) throws IOException {
        FileUtil fileUtil = new FileUtil("minMaxRiddle2.txt");
        InputStream inputStream = fileUtil.getFileFromResourceAsStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        List<String> arrItems = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .collect(toList());

        String[] arr = new String[n];
        arr = arrItems.toArray(arr);
        processNumbers(n, arr);

        String[] arrItems2 = {"1", "2", "3", "5", "1", "13", "3"};
        processNumbers(7, arrItems2);

        String[] arrItems3 = {"7", "6", "4", "4", "3", "2"};
        processNumbers(6, arrItems3);

        bufferedReader.close();
    }

    private static void processNumbers(int n, String[] arrItems) {
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            long arrItem = Long.parseLong(arrItems[i]);
            arr[i] = arrItem;
        }

        long[] res = riddle2(arr);

        for (int i = 0; i < res.length; i++) {
            System.out.print(String.valueOf(res[i]));

            if (i != res.length - 1) {
                System.out.print(" ");
            }
        }

        System.out.println(" ");
    }
}
