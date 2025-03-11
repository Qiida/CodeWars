package codo;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GreeterTest {
    @Test
    public void testSomething() {
        assertEquals("Hello, Ryan how are you doing today?", Greeter.greet("Ryan"));
    }
}
