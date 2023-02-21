package test;

import java.util.Hashtable;
import java.util.Map;

public class Proxify2 {
    public int solution2(String S, int[] C) {
        int resp = 0;
        Map<String, Integer> map = new Hashtable<>();

        String[] ch = S.split("");

        for(int i=0; i<ch.length; i++) {
            if (i+1<ch.length && ch[i].equals(ch[i+1])) {
                if (map.containsKey(ch[i]) && map.get(ch[i]) > C[i+1]) {
                    map.put(ch[i], C[i+1]);
                } else if (!map.containsKey(ch[i]) && C[i] < C[i+1]) {
                    map.put(ch[i], C[i]);
                } else if (!map.containsKey(ch[i]) && C[i] > C[i+1]) {
                    map.put(ch[i], C[i+1]);
                }
            } else if (map.size() > 0) {
                resp+=map.get(ch[i-1]);
                map.remove(ch[i-1]);
            }
        }

        for(Integer cost: map.values()) {
            resp+=cost;
        }

        return resp;
    }

    public int solution(String S, int[] C) {
        int resp = 0;

        String[] ch = S.split("");

        for(int i=0; i<ch.length; i++) {
            if (i+1<ch.length && ch[i].equals(ch[i+1])) {
                int[] cost = getLowerCost(i, ch, C, ch[i]);
                resp+=cost[0];
                i=cost[1];
            }
        }

        return resp;
    }

    private int[] getLowerCost(int index, String[] ch, int[] C, String character) {
        int[] resp = new int[2];

        resp[0] = C[index];

        for (int i=index; i<ch.length; i++) {
            if (!ch[i].equals(character)) {
                resp[1] = i - 1;
                break;
            }

            if (i+1<ch.length && ch[i].equals(ch[i+1])) {
                if (C[i] < C[i+1]) {
                    resp[0] = C[i];
                } else {
                    resp[0] = C[i+1];
                }
            }
        }

        return resp;
    }

    public static void main(String ... args) {
        Proxify2 t = new Proxify2();

        String a = "abcccbdccbb";
        int[] c = {0, 1, 3, 2, -1, 4, 5, 2, 1, 4, 5};
        System.out.println(t.solution2(a, c));
    }
}


