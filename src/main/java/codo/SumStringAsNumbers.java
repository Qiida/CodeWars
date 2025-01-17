package codo;

public class SumStringAsNumbers {
    public static String sumStrings(String a, String b) {
        if (b.length() < a.length()) {
            b = addLeadingZeros(a.length(), b);
        } else if (b.length() > a.length()) {
            a = addLeadingZeros(b.length(), a);
        }
        char[] char_digits_a = a.toCharArray();
        char[] char_digits_b = b.toCharArray();

        String stringSum = getStringSum(char_digits_a, char_digits_b);
        if (stringSum.startsWith("0")) {
            String[] splits = stringSum.split("^0+", 2);
            stringSum = splits[1];
        }
        return stringSum;
    }

    private static String getStringSum(char[] char_digits_a, char[] char_digits_b) {
        int leadingOne = 0;
        StringBuilder resultStringBuilder = new StringBuilder();
        for (int i = char_digits_a.length-1; i>=0; i--) {
            int int_digit_a = Integer.parseInt(String.valueOf(char_digits_a[i]));
            int int_digit_b = Integer.parseInt(String.valueOf(char_digits_b[i]));
            int sum = int_digit_a + int_digit_b;
            if (leadingOne == 1) {
                sum++;
                leadingOne--;
            }
            if (sum > 9 && i != 0) {
                leadingOne = 1;
                String sumString = String.valueOf(sum);
                resultStringBuilder.insert(0, sumString.charAt(1));
            } else {
                resultStringBuilder.insert(0, sum);
            }

        }
        return resultStringBuilder.toString();
    }

    private static String addLeadingZeros(int totalSize, String b) {
        int difference = totalSize - b.length();
        b = "0".repeat(difference) + b;
        return b;
    }
}
