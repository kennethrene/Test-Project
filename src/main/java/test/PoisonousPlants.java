package test;

import util.FileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PoisonousPlants {
    public static int poisonousPlants(List<Integer> p) {
        boolean die;
        int days = 0;
        int value;

        LinkedList<Integer> q = new LinkedList<>(p);
        int rightValue = -1;

        do {
            die = false;
            rightValue = -1;

            for(int i = q.size() - 1; i >= 0; i--) {
                value = q.get(i);

                if (rightValue != -1 && rightValue > value) {
                    rightValue = value;
                    q.remove(i + 1);
                    die = true;
                } else {
                    rightValue = value;
                }
            }

            if (die) {
                days++;
            }
        } while (die);

        return days;
    }

    public static int poisonousPlants2(List<Integer> p) {
        int days = 0;
        int l, c, r;
        int pl = 0, pc = 1, pr = 2;
        boolean end = false;

        do {
            if (pr < p.size()) {
                l = p.get(pl);
                c = p.get(pc);
                r = p.get(pr);

                if ((l < c) && (l < r)) {
                    if (pr + 1 < p.size() || p.size() == 3 || (pr == p.size() - 1 && c > r)) {
                        days++;
                    }

                    pc = pr + 1;
                    pr = pr + 2;
                } else if (l < c) {
                    days++;
                    pl = pr;
                    pc = pr + 1;
                    pr = pr + 2;
                } else if (l < r) {
                    if (pr + 1 < p.size() || p.size() == 3 || (pr == p.size() - 1 && c > r)) {
                        days++;
                    }

                    pl = pc;
                    pc = pr + 1;
                    pr = pr + 2;
                } else if (c < r) {
                    if (pr + 1 < p.size() || p.size() == 3 || (pr == p.size() - 1 && c > r)) {
                        days++;
                    }

                    pl = pc;
                    pc = pr + 1;
                    pr = pr + 2;
                } else {
                    pl = pr;
                    pc = pr + 1;
                    pr = pr + 2;
                }
            } else if (pc < p.size()) {
                l = p.get(pl);
                c = p.get(pc);

                if (l < c) {
                    days++;
                }

                end = true;
            }

            if (pc >= p.size() || pl > pc) {
                end = true;
            }
        } while(!end);

        return days;
    }

    public static void main(String[] args) throws IOException {
        FileUtil fileUtil = new FileUtil("poisonousPlants.txt");
        InputStream inputStream = fileUtil.getFileFromResourceAsStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> p = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        System.out.println(PoisonousPlants.poisonousPlants2(p));

        bufferedReader.close();
    }
}
