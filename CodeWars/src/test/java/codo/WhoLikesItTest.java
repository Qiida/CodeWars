package codo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WhoLikesItTest {

    @Test
    public void staticTests() {
        Assertions.assertEquals("no one likes this", WhoLikesIt.whoLikesIt());
        Assertions.assertEquals("Peter likes this", WhoLikesIt.whoLikesIt("Peter"));
        Assertions.assertEquals("Jacob and Alex like this", WhoLikesIt.whoLikesIt("Jacob", "Alex"));
        Assertions.assertEquals("Max, John and Mark like this", WhoLikesIt.whoLikesIt("Max", "John", "Mark"));
        Assertions.assertEquals("Alex, Jacob and 2 others like this", WhoLikesIt.whoLikesIt("Alex", "Jacob", "Mark", "Max"));
    }

}
