package globant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given an array of strings strs, group the anagrams together. You can return the answer in any order.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
*/
public class Anagram {
    public static void main(String[] args) {
        String[] input = new String[]{"eat","tea","tan","ate","nat","bat"};

        List<List<String>> resp = getAnagrams(input);
        System.out.println(Arrays.toString(input));
        System.out.println(resp);
        System.out.println("--------------");

        input = new String[]{};

        resp = getAnagrams(input);
        System.out.println(Arrays.toString(input));
        System.out.println(resp);
        System.out.println("--------------");

        input = new String[]{"", "a", "ab", "", "eat", "ate", "ate", "tea", "tan", "nat", "bat", "eas", "tia", "etr", "nan", "sae", "nano", "paco", "copa", "tester", "kenneth", "rene", "rana", "anar"};

        resp = getAnagrams(input);
        System.out.println(Arrays.toString(input));
        System.out.println(resp);
        System.out.println("--------------");
    }

    private static List<List<String>> getAnagrams(String[] input) {
        if (input == null || input.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> wordsMap = new HashMap<>();

        Arrays.stream(input).forEach(word -> {
            String[] letters = word.split("");
            Arrays.sort(letters);
            String sortedWord = String.join("", letters);

            wordsMap.putIfAbsent(sortedWord, new ArrayList<>());

            if (!wordsMap.get(sortedWord).contains(word)) {
                wordsMap.get(sortedWord).add(word);
            }
        });

        return new ArrayList<>(wordsMap.values());
    }
}

/* Test cases

[eat, tea, tan, ate, nat, bat]
[[eat, tea, ate], [bat], [tan, nat]]
--------------
[]
[]
--------------
['', a, ab, '', eat, ate, ate, tea, tan, nat, bat, eas, tia, etr, nan, sae, nano, paco, copa, tester, kenneth, rene, rana, anar]
[[], [nan], [a], [ab], [nano], [tan, nat], [etr], [rana, anar], [kenneth], [rene], [tia], [eas, sae], [paco, copa], [eat, ate, tea], [bat], [tester]]
--------------
*/
