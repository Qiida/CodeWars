package codo;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringIncrementer {
    public static String incrementString(String string) {
        Pattern numberPattern = Pattern.compile("([0-9]+)$");
        Matcher numberPatternMatch = numberPattern.matcher(string);
        StringBuilder incrementStringBuilder = new StringBuilder();
        if (numberPatternMatch.find()) {
            String numberString = numberPatternMatch.group();
            BigInteger bigInteger = new BigInteger(numberString);
            int size = numberString.length();
            incrementStringBuilder.append(string, 0, string.length()-size);
            incrementStringBuilder.append(String.format("%0"+size+"d",bigInteger.add(new BigInteger("1"))));
        } else {
            incrementStringBuilder.append(string).append("1");
        }
        return incrementStringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(incrementString("foo01"));
    }
}
