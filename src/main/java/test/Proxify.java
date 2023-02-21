package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Proxify {

    private String mixFunction(String[] arr, String prefix, List<String> list, int originalSize)  {
        if (arr.length == 1) {
            list.add(prefix);
            return arr[0];
        } else {
            for(int i = 0; i < arr.length; i++) {
                String p = prefix + arr[i];
                String[] arr2 = removeElement(arr, i);

                String resp = p + mixFunction(arr2, p, list, originalSize);

                if (arr2.length < originalSize - 1) {
                    list.add(resp);
                }
            }

            return "";
        }
    }

    private String[] removeElement(String[] arr, int index) {
        List<String> arrayList = new ArrayList<>(Arrays.asList(arr));
        arrayList.remove(index);

        String[] resp = new String[arr.length - 1];

        return arrayList.toArray(resp);
    }

    public int solution(String[] A) {
        int resp = 0;
        String[] filtered = getFilterStrings(A);

        List<String> listMixed = new ArrayList<>();
        mixFunction(filtered, "", listMixed, A.length);

        String[] mixed = new String[listMixed.size()];
        mixed = listMixed.toArray(mixed);

        String[] filteredMixed = getFilterStrings(mixed);

        if (filteredMixed.length > 0) {
            List<String> listMixedFiltered = Arrays.asList(filteredMixed);

            resp = listMixedFiltered.stream().map(c -> c.length()).sorted(Comparator.reverseOrder()).findFirst().get();
        }

        return resp;
    }

    private String[] getFilterStrings(String[] s) {
        List<String> resp = new ArrayList<>();

        for (String cad: s) {
            String[] temp = cad.split("");

            boolean dif = true;
            for (String ch: temp) {
                if(cad.indexOf(ch) != cad.lastIndexOf(ch)) {
                    dif = false;
                    break;
                }
            }

            if (dif) {
                resp.add(cad);
            }
        }

        String[] itemsArray = new String[resp.size()];
        return resp.toArray(itemsArray);
    }

    public static void main(String ... args) {
        Proxify t = new Proxify();

        String [] a = {"eva", "jqw", "tyn", "jan"};
        System.out.println(t.solution(a));

        String [] b = {"evaw", "jqw", "tywn"};
        System.out.println(t.solution(b));
    }
}
