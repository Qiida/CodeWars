package codo;
import codo.simplexer.Simplexer;
import codo.simplexer.Token;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;

public class SimplexerTest {

    @Test
    public void testEmpty() {
        Simplexer lexer = new Simplexer("");
        assertEquals(false, lexer.hasNext());
        lexer = new Simplexer(null);
    }

    @Test
    public void testSingle() {
        // Whitespace
        Simplexer lexer = new Simplexer(" \t");
        assertEquals(true, lexer.hasNext());
        assertEquals(new Token(" \t", "whitespace"), lexer.next());
        assertEquals(false, lexer.hasNext());
        // Identifier
        lexer = new Simplexer("x");
        assertEquals(true, lexer.hasNext());
        assertEquals(new Token("x", "identifier"), lexer.next());

        // Boolean
        lexer = new Simplexer("true");
        assertEquals(true, lexer.hasNext());
        assertEquals(new Token("true", "boolean"), lexer.next());

        // Integer
        lexer = new Simplexer("12345");
        assertEquals(true, lexer.hasNext());
        assertEquals(new Token("12345", "integer"), lexer.next());

        // String
        lexer = new Simplexer("\"Hello\"");
        assertEquals(true, lexer.hasNext());
        assertEquals(new Token("\"Hello\"", "string"), lexer.next());

        // Keyword
        lexer = new Simplexer("break");
        assertEquals(true, lexer.hasNext());
        assertEquals(new Token("break", "keyword"), lexer.next());

        // Operator
        lexer = new Simplexer("+");
        assertEquals(true, lexer.hasNext());
        assertEquals(new Token("+", "operator"), lexer.next());
    }

    @Test
    public void testExpression() {
        // Simple expression
        Simplexer lexer = new Simplexer("(1 + 2) - 5");
        assertEquals(Arrays.asList(new Token[] {
                new Token("(", "operator"),
                new Token("1", "integer"),
                new Token(" ", "whitespace"),
                new Token("+", "operator"),
                new Token(" ", "whitespace"),
                new Token("2", "integer"),
                new Token(")", "operator"),
                new Token(" ", "whitespace"),
                new Token("-", "operator"),
                new Token(" ", "whitespace"),
                new Token("5", "integer")
        }), toList(lexer));
    }

    @Test
    public void testStatement() {
        // Simple statement.
        Simplexer lexer = new Simplexer("return x + 1");
        assertEquals(Arrays.asList(new Token[] {
                new Token("return", "keyword"),
                new Token(" ", "whitespace"),
                new Token("x", "identifier"),
                new Token(" ", "whitespace"),
                new Token("+", "operator"),
                new Token(" ", "whitespace"),
                new Token("1", "integer")
        }), toList(lexer));
    }

    private List<Token> toList(Simplexer lexer) {
        List<Token> tokens = new ArrayList<>();
        lexer.forEachRemaining(tokens::add);
        return tokens;
    }

}
