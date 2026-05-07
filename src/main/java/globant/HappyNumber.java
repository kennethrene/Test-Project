package globant;

/*
Write an algorithm to determine if a number n is happy.
A happy number is a number defined by the following process:
Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.
*/
public class HappyNumber {
    public static void main(String[] args){
        long input = 19;
        System.out.println(input + ": " + isHappy(input));
        System.out.println("-".repeat(12));

        input = 2;
        System.out.println(input + ": " + isHappy(input));
        System.out.println("-".repeat(12));

        input = 12;
        System.out.println(input + ": " + isHappy(input));
        System.out.println("-".repeat(12));

        input = 23;
        System.out.println(input + ": " + isHappy(input));
        System.out.println("-".repeat(12));
    }

    private static boolean isHappy(long n) {
        if (n == 1) return true;
        if (n == 4) return false;

        long sum = 0;

        do {
            long digit = n % 10;
            sum += digit * digit;
            n /= 10;
        } while (n > 0);

        if (sum == 1) return true;
        else return isHappy(sum);
    }
}

/*
Tests

19: true
------------
2: false
------------
12: false
------------
23: true
------------
*/