package src.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.string_and_math.ContainsOnlyDigit;
import src.test.time_tracker.ExecutionTimeTracker;

public class ContainsOnlyDigitTest extends ExecutionTimeTracker {
    private final ContainsOnlyDigit testClass = new ContainsOnlyDigit();

    private final String GIVEN_RIGHT_INPUT = "123123";
    private final String GIVEN_WRONG_INPUT = "sdk124125";

    @Test
    public void v1Right() {
        var result = testClass.v1(GIVEN_RIGHT_INPUT);
        Assertions.assertTrue(result);
    }

    @Test
    public void v2Right() {
        var result = testClass.v2(GIVEN_RIGHT_INPUT);
        Assertions.assertTrue(result);
    }

    @Test
    public void v3Right() {
        var result = testClass.v3(GIVEN_RIGHT_INPUT);
        Assertions.assertTrue(result);
    }

    @Test
    public void v1Wrong() {
        var result = testClass.v1(GIVEN_WRONG_INPUT);
        Assertions.assertFalse(result);
    }

    @Test
    public void v2Wrong() {
        var result = testClass.v2(GIVEN_WRONG_INPUT);
        Assertions.assertFalse(result);
    }

    @Test
    public void v3Wrong() {
        var result = testClass.v3(GIVEN_WRONG_INPUT);
        Assertions.assertFalse(result);
    }

}
