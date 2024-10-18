package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
  /*  - empty string : ""
- one char, too many closed: "("
- one char, too many opened: ")"
- one char, balanced: "a"
- multi char, too many open: "({}"
- multi char, too many closed: "{}]"
- multi char, balanced, good nesting: "({{([])}})[{}]"
- multi char, balanced, bad nesting: "(([)){]}" */

    @Test
    public void emptyString(){
        assertTrue(isBalanced(""));
    }

    @Test
    public void oneCharTooManyClosed(){
        assertFalse(isBalanced("("));
    }

    @Test
    public void oneCharTooManyOpened(){
        assertFalse(isBalanced(")"));
    }

    @Test
    public void oneCharBalanced(){
        assertTrue(isBalanced("a"));
    }

    @Test
    public void multiCharTooManyOpen() {
        assertFalse(isBalanced("({}"));
    }

    @Test
    public void multiCharTooManyClosed() {
        assertFalse(isBalanced("{}]"));
    }

    @Test
    public void multiCharBalancedGoodNest() {
        assertTrue(isBalanced("({{([])}})[{}]"));
    }

    @Test
    public void multiCharBalancedBadNest() {
        assertFalse(isBalanced("(([)){]}"));
    }
}