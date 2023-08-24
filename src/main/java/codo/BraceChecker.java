package codo;

public class BraceChecker {

    public boolean isValid(String braces) {
        Integer[] singularity = getSingularity(braces);

        if (singularity == null) {
            return false;
        } else {
            String bracesSub = getNextBracesString(braces, singularity);
            if (bracesSub.isEmpty()) {
                return true;
            }
            return isValid(bracesSub);
        }
    }

    private Character getClosingBrace(Character opening) {
        return switch (opening) {
            case '(' -> ')';
            case '[' -> ']';
            case '{' -> '}';
            default -> null;
        };
    }

    private Integer[] getSingularity(String braces) {
        for (int i=0; i<braces.length()-1; i++) {
            Integer[] singularity = new Integer[2];
            singularity[0] = i;
            singularity[1] = i+1;
            Character closingBrace = getClosingBrace(braces.charAt(singularity[0]));
            if (closingBrace == null) {
                return null;
            }
            if (braces.charAt(singularity[1]) == closingBrace) {
                return singularity;
            }
        }
        return null;
    }

    private String getNextBracesString(String braces, Integer[] singularity) {
        StringBuilder stringBuilder = new StringBuilder(braces);
        stringBuilder.delete(singularity[0], singularity[1]+1);
        return stringBuilder.toString();
    }

}

