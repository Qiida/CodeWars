package codo;

import java.util.Stack;



public class TextAligner {
    public static String justify(String text, int width) {
        StringBuilder alignedText = new StringBuilder();
        Stack<String> wordStack = getWordStack(text);

        while (!wordStack.isEmpty()) {
            StringBuilder line = new StringBuilder();
            while (line.length() < width) {
                if (wordStack.isEmpty()) {
                    break;
                }
                String word = wordStack.peek();
                if (!line.toString().isEmpty()) {
                    word = " " + word;
                }
                int lineLength = (line + word).length();
                if (lineLength < width) {
                    line.append(word);
                    wordStack.pop();
                } else if (lineLength == width) {
                    line.append(word);
                    wordStack.pop();
                    break;
                } else {
                    break;
                }
            }
            if (line.length() < width && !wordStack.isEmpty()) {
                String[] lineWords = line.toString().split("\\s+");
                int totalLength = 0;
                for (String lineWord : lineWords) {
                    totalLength += lineWord.length();
                }
                int numberOfWhitespacesRequired = width - totalLength;
                int numberOfWhitespacesRequiredInit = numberOfWhitespacesRequired;
                while (numberOfWhitespacesRequired != 0) {
                    for (int i=0; i<lineWords.length-1; i++) {
                        if (numberOfWhitespacesRequired != 0) {
                            lineWords[i] += " ";
                            numberOfWhitespacesRequired--;
                        }
                    }
                    if (numberOfWhitespacesRequired == numberOfWhitespacesRequiredInit) {
                        break;
                    }
                }
                line = new StringBuilder();
                for (String lineWord : lineWords) {
                    line.append(lineWord);
                }
            }
            if (!wordStack.isEmpty()) {
                line.append("\n");
            }
            alignedText.append(line);
        }
        return alignedText.toString();
    }

    private static Stack<String> getWordStack(String text) {
        String[] words = text.split("\\s+");
        Stack<String> wordStack = new Stack<>();
        for (String word : words) {
            wordStack.push(word);
        }
        Stack<String> wordStackReversed = new Stack<>();
        while (!wordStack.isEmpty()) {
            wordStackReversed.push(wordStack.pop());
        }
        return wordStackReversed;
    }
}
