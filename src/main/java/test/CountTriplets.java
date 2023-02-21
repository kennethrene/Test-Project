package test;

import util.FileUtil;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CountTriplets {
    static int cont = 0;
    static int index1 = 0;
    static int index2 = 1;
    static int index3 = 2;
    static int countTriplets = 0;
    static int size = 0;

    static long countTriplets(List<Long> arr, long r) {
        long numberOfTriplets = 0L;
        Map<Long, Long> mapValueCounting = new HashMap<>();
        Map<Long, Long> mapPair = new HashMap<>();

        for (int i = arr.size() - 1; i >= 0; i--) {
            long a = arr.get(i);
            if (mapPair.containsKey(a * r)) {
                numberOfTriplets += mapPair.get(a * r);
            }
            if (mapValueCounting.containsKey(a * r)) {
                mapPair.put(a, mapPair.getOrDefault(a, 0L) + mapValueCounting.get(a * r));
            }
            mapValueCounting.merge(a, 1L, Long::sum);
        }
        return numberOfTriplets;
    }

    static long countTriplets3(List<Long> arr, long r) {
        if (arr.size() < 3) {
            return 0;
        }

        size = arr.size();

        while (index1 <= size - 3) {
            int nextIndex2 = getNextIndex(arr, index1 + 1, arr.get(index1) * r);

            while (nextIndex2 != -1) {
                index2 = nextIndex2;

                int nextIndex3 = getNextIndex(arr, index2 + 1, arr.get(index2) * r);

                while (nextIndex3 != -1) {
                    index3 = nextIndex3;
                    System.out.println("(" + index1 + "," + index2 + "," + index3 + ")");
                    countTriplets++;

                    nextIndex3 = getNextIndex(arr, nextIndex3 + 1, arr.get(index2) * r);
                }

                nextIndex2 = getNextIndex(arr, index2 + 1, arr.get(index1) * r);
            }

            index1++;
        }

        return countTriplets;
    }

    static long countTriplets2(List<Long> arr, long r) {
        if (arr.size() < 3) {
            return 0;
        }

        size = arr.size();
        int nextIndex = 0;

        while(index1 <= size - 3) {
            checkElement2(arr, r);

            if (index3 + 1 < size - 1) {
                nextIndex = arr.subList(index3 + 1, arr.size()).indexOf(arr.get(index2) * r);

                if (nextIndex != -1) {
                    index3++;
                } else if (index2 + 1 < size - 2) {
                    nextIndex = arr.subList(index2 + 1, arr.size()).indexOf(arr.get(index1) * r);

                    if (nextIndex != -1) {
                        index2++;
                        index3 = index2 + 1;
                    } else if(index1 + 1 <= size - 3) {
                        index1++;
                        index2 = index1 + 1;
                        index3 = index1 + 2;
                    }
                } else if(index1 + 1 <= size - 3) {
                    index1++;
                    index2 = index1 + 1;
                    index3 = index1 + 2;
                }
            } else if (index2 + 1 < size - 2) {
                nextIndex = arr.subList(index2 + 1, arr.size()).indexOf(arr.get(index1) * r);

                if (nextIndex != -1) {
                    index2++;
                    index3 = index2 + 1;
                }
            } else {
                index1++;
                index2 = index1 + 1;
                index3 = index1 + 2;
            }
        }

        return countTriplets;
    }

    static private int getNextIndex(List<Long> arr, int fromIndex, long valueToFind) {
        int nextIndex = arr.subList(fromIndex, arr.size()).indexOf(valueToFind);

        if (nextIndex == -1) {
            return -1;
        } else {
            return fromIndex + nextIndex;
        }
    }

    static private boolean isFactor(List<Long> arr, int index1, int index2, long r) {
        return arr.get(index1) * r == arr.get(index2) ? true : false;
    }

    static private int checkElement2(List<Long> arr, long r) {
        cont++;
        long e1 = arr.get(index1);
        long e2 = arr.get(index2);
        long e3 = arr.get(index3);
        int nextIndex = 0;

        System.out.println("("+index1+","+index2+","+index3+")");

        if (e2 * r != e3) {
            nextIndex = getNextIndex(arr, index2 + 1, e2 * r);

            if (nextIndex != -1) {
                index3 = nextIndex;
                return checkElement2(arr, r);
            } else if (index2 + 1 <= size - 2) {
                index2 = index2 + 1;
                index3 = index3 + 2;
                return checkElement2(arr, r);
            } else if(index1 + 1 <= size - 3) {
                index1 = index1 + 1;
                index2 = index1 + 2;
                index3 = index1 + 3;
                return checkElement2(arr, r);
            }
        } else if (e1 * r != e2) {
            nextIndex = getNextIndex(arr, index1 + 1, e1 * r);

            if (nextIndex != -1) {
                index2 = nextIndex;
                index3 = nextIndex + 1;
                return checkElement2(arr, r);
            } else if(index1 + 1 <= size - 3) {
                index1 = index1 + 1;
                index2 = index1 + 2;
                index3 = index1 + 3;
                return checkElement2(arr, r);
            }
        } else {
            System.out.println("("+index1+","+index2+","+index3+") : " + cont);
            ++countTriplets;
        }

        return countTriplets;
    }

    static private int checkElement(List<Long> arr, int index1, int index2, int index3, long r, int countTriplets, int size) {
        cont++;
        long e1 = arr.get(index1);
        long e2 = arr.get(index2);
        long e3 = arr.get(index3);

        System.out.println("("+index1+","+index2+","+index3+") : " + cont);

        if (e2 * r != e3) {
            if (index3 + 1 <= size - 1 && arr.get(index3 + 1) <= e2 * r) {
                return checkElement(arr, index1, index2, index3 + 1, r, countTriplets, size);
            }
        } else if (e1 * r != e2) {
            if (index3  + 1 <= size - 1 && arr.get(index3 + 1) <= e2 * r) {
                return checkElement(arr, index1, index2, index3 + 1, r, countTriplets, size);
            } else if (index2 + 1 <= size - 2 && index3  + 1 <= size - 1 && arr.get(index2 + 1) <= e1 * r && arr.get(index3 + 1) <=  arr.get(index2 + 1) * r) {
                return checkElement(arr, index1, index2 + 1, index3 + 1, r, countTriplets, size);
            }
        } else {
            System.out.println("("+index1+","+index2+","+index3+") : " + cont);
            ++countTriplets;
        }

        if (index3 + 1 <= size - 1
                && arr.get(index3 + 1) <= e2 * r) {

            return checkElement(arr, index1, index2, index3 + 1, r, countTriplets, size);

        } else if (index2 + 1 <= size - 2
                && arr.get(index2 + 1) <= arr.get(index1) * r
                && arr.get(index2 + 1) <= arr.get(index2 + 2) * r) {

            return checkElement(arr, index1, index2 + 1, index2 + 2, r, countTriplets, size);

        } else if (index1 + 1 <= size - 3
                && arr.get(index1 + 2) <= arr.get(index1 + 1) * r) {

            return checkElement(arr, index1 + 1, index1 + 2, index1 + 3, r, countTriplets, size);
        }

        return countTriplets;
    }

    public static void main(String[] args) throws IOException {
        FileUtil fileUtil = new FileUtil("countTriplets0.txt");
        InputStream inputStream = fileUtil.getFileFromResourceAsStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        System.out.println(ans);

        bufferedReader.close();
    }
}
