package codo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RailFenceCypherTest {
    @Test
    public void encodeSampleTests() {
        String[][] encodes = {
                {"WEAREDISCOVEREDFLEEATONCE", "WEEEAALTRFOEDNDECIRESECVO"}, // 10 rails
                {"WEAREDISCOVEREDFLEEATONCE", "WECRLTEERDSOEEFEAOCAIVDEN"},  // 3 rails
                {"Hello, World!", "Hoo!el,Wrdl l"},    // 3 rails
                {"Hello, World!", "H !e,Wdloollr"},    // 4 rails
                {"", ""},                  // 3 rails (even if...)
        };
        int[] rails = {10,3,3,4,3};
        for (int i=0 ; i<encodes.length ; i++) {
            assertEquals(encodes[i][1], RailFenceCipher.encode(encodes[i][0], rails[i]));
        }
    }


    @Test
    public void decodeSampleTests() {
        String[][] decodes = {
                {"WEEEAALTRFOEDNDECIRESECVO", "WEAREDISCOVEREDFLEEATONCE"},  // 10 rails
                {"WECRLTEERDSOEEFEAOCAIVDEN", "WEAREDISCOVEREDFLEEATONCE"},    // 3 rails
                {"H !e,Wdloollr", "Hello, World!"},    // 4 rails
                {"WECRLTEERDSOEEFEAOCAIVDEN", "WEAREDISCOVEREDFLEEATONCE"},    // 3 rails
                {"", ""}                               // 3 rails (even if...)
        };
        int[] rails = {10,3,4,3,3};
        for (int i=0 ; i<decodes.length ; i++) {
            assertEquals(decodes[i][1], RailFenceCipher.decode(decodes[i][0], rails[i]));
        }
    }
}
