package src.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.string_and_math.ReverseWords;
import src.test.time_tracker.ExecutionTimeTracker;

public class ReverseWordsTest extends ExecutionTimeTracker {
    private final ReverseWords testClass = new ReverseWords();

    private final String GIVEN_TEXT = "12441 12441 12441 12441 12441 12441 12441";
    private final String EXPECTED_TEXT = "14421 14421 14421 14421 14421 14421 14421";

    @Test
    public void v1() {
        var result = testClass.v1(GIVEN_TEXT);
        Assertions.assertEquals(EXPECTED_TEXT, result);
    }
    @Test
    public void v2() {
        var result = testClass.v2(GIVEN_TEXT);
        Assertions.assertEquals(EXPECTED_TEXT, result);
    }
    @Test
    public void v3() {
        var result = testClass.v3(GIVEN_TEXT);
        Assertions.assertEquals(EXPECTED_TEXT, result);
    }
}
