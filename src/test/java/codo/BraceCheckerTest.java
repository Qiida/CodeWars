package codo;


import codo.BraceChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BraceCheckerTest {

    private final BraceChecker checker = new BraceChecker();

    @Test
    public void testValid0() {
        Assertions.assertTrue(checker.isValid("([])"));
    }
    @Test
    public void testValid1() {
        Assertions.assertTrue(checker.isValid("(){}[]"));
    }
    @Test
    public void testValid2() {
        Assertions.assertTrue(checker.isValid("({})[({})]"));
    }
    @Test
    public void testValid3() {
        Assertions.assertTrue(checker.isValid("{}({})[]"));
    }
    @Test
    public void testValid4() {
        Assertions.assertTrue(checker.isValid("(({{[[]]}}))"));
    }


    @Test
    public void testInvalid0() {
        Assertions.assertFalse(checker.isValid("[(])"));
    }
    @Test
    public void testInvalid1() {
        Assertions.assertFalse(checker.isValid("(})"));
    }
    @Test
    public void testInvalid2() {
        Assertions.assertFalse(checker.isValid("}}]]))}])"));
    }
    @Test
    public void testInvalid3() {
        Assertions.assertFalse(checker.isValid("}(}]]))}])"));
    }
    @Test
    public void testInvalid4() {
        Assertions.assertFalse(checker.isValid("())({}}{()][]["));
    }

}