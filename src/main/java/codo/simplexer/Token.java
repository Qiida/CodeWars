package codo.simplexer;

import java.util.Objects;

public class Token {

    public final String text;
    public final String type;

    public Token(String text, String type) {
        this.text = text;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.text == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Token token = (Token) obj;
        return Objects.equals(text, token.text) && Objects.equals(type, token.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, text);
    }
}