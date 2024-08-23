package codo;

import org.junit.Test;
import static org.junit.Assert.*;

public class TimeFormatterTest {
    @Test
    public void exampleTests() {
        assertEquals("23 hours, 52 minutes and 55 seconds", TimeFormatter.formatDuration(85_975));
        assertEquals("1 year", TimeFormatter.formatDuration(31_536_000));
        assertEquals("1 year and 1 day", TimeFormatter.formatDuration(31_622_400));
        assertEquals("1 second", TimeFormatter.formatDuration(1));
        assertEquals("1 minute and 2 seconds", TimeFormatter.formatDuration(62));
        assertEquals("2 minutes", TimeFormatter.formatDuration(120));
        assertEquals("1 hour", TimeFormatter.formatDuration(3600));
        assertEquals("1 hour, 1 minute and 2 seconds", TimeFormatter.formatDuration(3662));
    }
}