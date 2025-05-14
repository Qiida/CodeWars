package codo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarMileage {
    public static int isInteresting(int number, int[] awesomePhrases) {
        if (number < 98) {
            return 0;
        }
        for (int awesomePhrase : awesomePhrases) {
            if (isAwesome(number, awesomePhrase)) {
                return 2;
            }
            for (int increment = 1; increment <= 2; increment++) {
                if (isAwesome(number+increment, awesomePhrase)) {
                    return 1;
                }
            }
        }
        if (
                number > 99 && (
                isFollowedByZerosOrDigitsAreSame(number) ||
                isIncrementing(number) ||
                isDecrementing(number) ||
                isPalindrome(number))
        ) {
            return 2;
        }
        for (int increment = 1; increment <= 2; increment++) {
            if (
                    isFollowedByZerosOrDigitsAreSame(number+increment) ||
                    isIncrementing(number+increment) ||
                    isDecrementing(number+increment) ||
                    isPalindrome(number+increment)
            ) {
                return 1;
            }
        }
        return 0;
    }

    private static boolean isFollowedByZerosOrDigitsAreSame(int number) {
        Pattern pattern = Pattern.compile("\\d0{2,}|^(\\d)\\1{2,}$");
        Matcher matcher = pattern.matcher(String.valueOf(number));
        return matcher.find();
    }

    private static boolean isAwesome(int number, int awesomePhrase) {
        return number == awesomePhrase;
    }

    private static boolean isIncrementing(int number) {
        boolean isIncrementing = true;
        char[] digitChars = String.valueOf(number).toCharArray();
        int previousDigit = Character.getNumericValue(digitChars[0]);
        for (int i=1; i<digitChars.length; i++) {
            int digitInt = Character.getNumericValue(digitChars[i]);
            if (
                    (previousDigit+1 != digitInt) &&
                            !(previousDigit == 9 && digitInt == 0))
            {
                return false;
            }
            previousDigit = digitInt;
        }
        return isIncrementing;
    }

    private static boolean isDecrementing(int number) {
        boolean isDecrementing = true;
        char[] digitChars = String.valueOf(number).toCharArray();
        int previousDigit = Character.getNumericValue(digitChars[0]);
        for (int i=1; i<digitChars.length; i++) {
            int digitInt = Character.getNumericValue(digitChars[i]);
            if (
                    (previousDigit-1 != digitInt) &&
                            !(previousDigit == 0 && digitInt == 1))
            {
                isDecrementing = false;
            }
            previousDigit = digitInt;
        }
        return isDecrementing;
    }

    private static boolean isPalindrome(int number) {
        char[] digitChars = String.valueOf(number).toCharArray();
        if (digitChars.length % 2 == 0) {
            for (int i=0; i<digitChars.length/2; i++) {
                if (digitChars[i] != digitChars[digitChars.length-1-i]) {
                    return false;
                }
            }
        } else {
            for (int i=0; i<digitChars.length/2; i++) {
                if (digitChars[i] != digitChars[digitChars.length-1-i]) {
                    return false;
                }
            }
        }
        return true;
    }
}


