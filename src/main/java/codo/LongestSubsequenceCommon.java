package codo;

public class LongestSubsequenceCommon {
    public static String run(String s1, String s2) {
        String lsc = "";
        StringBuilder lscBuilder = new StringBuilder();
        for (char c : s1.toCharArray()) {
            lscBuilder.append(c);
            if (lscBuilder.length() > lsc.length() && s2.contains(lscBuilder.toString())) {
                lsc = lscBuilder.toString();
            }

        }
        return lsc;
    }
}
