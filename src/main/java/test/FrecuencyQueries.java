package test;

import util.FileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FrecuencyQueries {

    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> resp = new ArrayList<>();
        Map<Integer, Integer> numbers = new HashMap<>();
        Map<Integer, Integer> freq = new HashMap<>();
        int op = 0;
        int val = 0;
        int newVal = 0;

        for (List<Integer> query: queries) {
            op = query.get(0);
            val = query.get(1);

            if (op == 1) {
                newVal = numbers.getOrDefault(val, 0) + 1;
                numbers.put(val, newVal);
                freq.merge(newVal, 1, Integer::sum);

                if (newVal > 1) {
                    if (freq.get(newVal - 1) > 1) {
                        freq.merge(newVal - 1, 1, (c, o) -> c - 1);
                    } else {
                        freq.remove(newVal - 1);
                    }
                }
            } else if (op == 2 && numbers.containsKey(val)) {
                if (numbers.get(val) > 1) {
                    newVal = numbers.get(val) - 1;
                    numbers.put(val, newVal);
                    freq.merge(newVal, 1, Integer::sum);

                    if (freq.get(newVal + 1) > 1) {
                        freq.merge(newVal + 1, 0, (c, o) -> c - 1);
                    } else {
                        freq.remove(newVal + 1);
                    }
                } else {
                    numbers.remove(val);

                    if (freq.get(1) > 1) {
                        freq.put(1,freq.get(1) - 1);
                    } else {
                        freq.remove(1);
                    }
                }
            } else if (op == 3) {
                resp.add(freq.containsKey(val) ? 1 : 0);
            }
        }

        return resp;
    }


    public static void main(String[] args) throws IOException {
        FileUtil fileUtil = new FileUtil("frecuencyQueries2.txt");
        InputStream inputStream = fileUtil.getFileFromResourceAsStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        String resp = ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
                + "\n";

        System.out.println(resp);

        bufferedReader.close();
    }
}
