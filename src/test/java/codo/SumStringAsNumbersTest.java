package codo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumStringAsNumbersTest {
    @Test
    void test() {
        assertEquals("151", SumStringAsNumbers.sumStrings("19", "132"));
        assertEquals("579", SumStringAsNumbers.sumStrings("123", "456"));
        assertEquals("10367", SumStringAsNumbers.sumStrings("800", "9567"));
        assertEquals("20547647310314204863694119", SumStringAsNumbers.sumStrings("00020547647310267018241017455", "47186622676664"));
    }
}