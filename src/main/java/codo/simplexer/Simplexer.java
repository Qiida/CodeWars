package codo.simplexer;

import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Simplexer
        implements Iterator<Token> {

    private enum Type {
        WHITESPACE, BOOLEAN, INTEGER, STRING, KEYWORD, IDENTIFIER, OPERATOR, NULL
    }

    final private Vector<Token> tokens = new Vector<>();
    public Simplexer(String buffer) {
        if (buffer == null) {
            return;
        }

        String[] splits = buffer.split("(?<=\\s)|(?=\\s)|(?<=[\\[\\]+\\-*\\/%()=])|(?=[\\[\\]+\\-*\\/%()=])");
        for (String split : splits) {
            Type type = getTypeOfString(split);
            switch (type) {
                case BOOLEAN -> {
                    tokens.add(new Token(split, "boolean"));
                }
                case INTEGER -> {
                    tokens.add(new Token(split, "integer"));
                }
                case STRING -> {
                    tokens.add(new Token(split, "string"));
                }
                case KEYWORD -> {
                    tokens.add(new Token(split, "keyword"));
                }
                case IDENTIFIER -> {
                    tokens.add(new Token(split, "identifier"));
                }
                case OPERATOR -> {
                    tokens.add(new Token(split, "operator"));
                }
                case WHITESPACE -> {
                    if (tokens.isEmpty() || !tokens.get(tokens.size() - 1).equals(new Token(" ", "whitespace"))) {
                        tokens.add(new Token(split, "whitespace"));
                    } else {
                        Token token = tokens.get(tokens.size() - 1);
                        tokens.remove(token);
                        tokens.add(new Token(token.text+split, "whitespace"));
                    }
                }
                case NULL -> {

                }
            }
        }
    }

    private Type getTypeOfString(String buffer) {
        final Pattern whitespacePattern = Pattern.compile("\\s");
        final Matcher whitespaceMatcher = whitespacePattern.matcher(buffer);
        if (whitespaceMatcher.find()) {
            return Type.WHITESPACE;
        }
        final Pattern booleanPattern = Pattern.compile("true|false");
        Matcher booleanMatcher = booleanPattern.matcher(buffer);
        if (booleanMatcher.find()) {
            return Type.BOOLEAN;
        }
        final Pattern integerPattern = Pattern.compile("-?\\d+");
        Matcher integerMatcher = integerPattern.matcher(buffer);
        if (integerMatcher.find()) {
            return Type.INTEGER;
        }
        final Pattern stringPattern = Pattern.compile("\".+\"");
        Matcher stringMatcher = stringPattern.matcher(buffer);
        if (stringMatcher.find()) {
            return Type.STRING;
        }
        final Pattern keywordPattern = Pattern.compile("if|else|for|while|return|func|break");
        Matcher keywordMatcher = keywordPattern.matcher(buffer);
        if (keywordMatcher.find()) {
            return Type.KEYWORD;
        }
        final Pattern identifierPattern = Pattern.compile("[A-Za-z_$][A-Za-z0-9_$]*");
        Matcher identifierMatcher = identifierPattern.matcher(buffer);
        if (identifierMatcher.find()) {
            return Type.IDENTIFIER;
        }
        final Pattern operatorPattern = Pattern.compile("[\\[\\]+\\-*\\/%()=]");
        Matcher operatorMatcher = operatorPattern.matcher(buffer);
        if (operatorMatcher.find()) {
            return Type.OPERATOR;
        }
        return Type.NULL;
    }

    @Override
    public boolean hasNext() {
        return !tokens.isEmpty();
    }

    @Override
    public Token next() {
        Token next = tokens.get(0);
        tokens.remove(0);
        return next;
    }
}
