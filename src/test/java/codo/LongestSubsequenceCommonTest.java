package codo;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

public class LongestSubsequenceCommonTest {
    @Test
    public void exampleTests() {
        assertEquals("", LongestSubsequenceCommon.run("a", "b"));
        assertEquals("abc", LongestSubsequenceCommon.run("abcdef", "abc"));
        assertEquals("abc", LongestSubsequenceCommon.run("abcdef", "abc"));
        assertEquals("acf", LongestSubsequenceCommon.run("abcdef", "acf"));
        assertEquals("12356", LongestSubsequenceCommon.run("132535365", "123456789"));
    }
}