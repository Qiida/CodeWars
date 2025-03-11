package codo;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class NumberToStringConverterTest {
    @Test
    public void tests() {
        assertEquals("67", NumberToStringConverter.numberToString(67));
        assertEquals("123", NumberToStringConverter.numberToString(123));
        assertEquals("999", NumberToStringConverter.numberToString(999));
        assertEquals("0", NumberToStringConverter.numberToString(0));
    }
}
