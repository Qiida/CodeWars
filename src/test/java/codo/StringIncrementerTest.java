package codo;




import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

// TODO: Replace examples and use TDD by writing your own tests

public class StringIncrementerTest {

    private static void doTest(String str, String expected) {
        String actual = assertDoesNotThrow(() -> StringIncrementer.incrementString(str), "Solution thrown an unexpected exception for str=\"" + str + "\"\n\n");
        assertEquals(expected, actual, "Incorrect answer for str=\"" + str + "\"\n\n");
    }


    @Test
    public void exampleTests() {
        doTest("foobar000", "foobar001");
        doTest("foo", "foo1");
        doTest("foobar001", "foobar002");
        doTest("foobar99", "foobar100");
        doTest("foobar099", "foobar100");
        doTest("", "1");
        doTest("fo99obar99", "fo99obar100");
        doTest("0000052324762850166620203", "0000052324762850166620204");
    }
}
