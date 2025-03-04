package test;

import util.FileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ClimbingNumbers {

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        Map<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        int pos = 1;
        List<Integer> resp = new ArrayList<>();

        for(Integer v: ranked) {
            if (!map.containsKey(v)) {
                map.put(v, pos++);
            }
        }

        int playerPos = map.size() + 1;
        for (Integer v: player) {
            for(Map.Entry<Integer, Integer> m: map.entrySet()) {
                if (v >= m.getKey()) {
                    playerPos = m.getValue();
                    break;
                }
            }
            resp.add(playerPos);
            System.out.println(playerPos);
        }

        return resp;
    }

    public static List<Integer> climbingLeaderboard2(List<Integer> ranked, List<Integer> player) {
        List<Integer> l = new ArrayList<>();
        List<Integer> resp = new ArrayList<>();

        int pos = 0;

        for(Integer v: ranked) {
            if (!l.contains(v)) {
                l.add(pos++, v);
            }
        }

        int playerPos = l.size() + 1;
        for (Integer v: player) {
            for(int i = 0; i < l.size(); i++) {
                if (v >= l.get(i)) {
                    playerPos = i + 1;
                    break;
                }
            }
            resp.add(playerPos);
            System.out.println(playerPos);
        }

        return resp;
    }

    public static List<Integer> climbingLeaderboard3(List<Integer> ranked, List<Integer> player) {
        List<Integer> l = new ArrayList<>();
        List<Integer> resp = new ArrayList<>();

        int pos = 0;

        for(Integer v: ranked) {
            if (!l.contains(v)) {
                l.add(pos++, v);
            }
        }

        int playerPos;
        int posIni = l.size() - 1;
        for (Integer v: player) {
            for(int i = posIni; i >= 0; i--) {
                if (v < l.get(i) && i > 0) {
                    playerPos = i + 2;

                    if(posIni != playerPos - 1) {
                        posIni = playerPos - 2;
                    }

                    resp.add(playerPos);
                    System.out.println(playerPos);

                    break;
                } else if(i == 0) {
                    if (v >= l.get(i)) {
                        resp.add(1);
                        System.out.println(1);
                    } else {
                        resp.add(2);
                        System.out.println(2);
                    }
                }
            }
        }

        return resp;
    }

    public static List<Integer> climbingLeaderboard3_1(List<Integer> ranked, List<Integer> player) {
        TreeSet<Integer> ls = new TreeSet<>(Collections.reverseOrder());
        List<Integer> resp = new ArrayList<>();

        ls.addAll(ranked);
        List<Integer> l = new ArrayList<>(ls);

        int playerPos;
        int posIni = l.size() - 1;
        for (Integer v: player) {
            for(int i = posIni; i >= 0; i--) {
                if (v < l.get(i) && i > 0) {
                    playerPos = i + 2;

                    if(posIni != playerPos - 1) {
                        posIni = playerPos - 2;
                    }

                    resp.add(playerPos);
                    System.out.println(playerPos);

                    break;
                } else if(i == 0) {
                    if (v >= l.get(i)) {
                        resp.add(1);
                        System.out.println(1);
                    } else {
                        resp.add(2);
                        System.out.println(2);
                    }
                }
            }
        }

        return resp;
    }

    public static List<Integer> climbingLeaderboard4(List<Integer> ranked, List<Integer> player) {
        TreeSet<Integer> ls = new TreeSet<>(Collections.reverseOrder());
        List<Integer> resp = new ArrayList<>();

        ls.addAll(ranked);
        List<Integer> l = new ArrayList<>(ls);

        int position = 0;

        for (Integer v: player) {
            try {
                if (v >= l.get(l.size() - 1) && v < l.get(0) ) {
                    position = Collections.binarySearch(l, v);
                } else if (v < l.get(l.size() - 1)) {
                    position = l.size() + 1;
                } else if (v >= l.get(0)) {
                    position = 1;
                }
            } catch (NullPointerException e) {
                position = 1;
            }

            System.out.println(position);
        }

        return resp;
    }

    public static void main(String[] args) throws IOException {
        FileUtil fileUtil = new FileUtil("test.txt");
        InputStream inputStream = fileUtil.getFileFromResourceAsStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = ClimbingNumbers.climbingLeaderboard3_1(ranked, player);

        bufferedReader.close();
    }
}
