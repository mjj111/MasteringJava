package src.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.string_and_math.FirstNonRepeatedCharacter;
import src.test.time_tracker.ExecutionTimeTracker;

public class FirstNonRepeatedCharacterTest extends ExecutionTimeTracker {
    private final FirstNonRepeatedCharacter testClass = new FirstNonRepeatedCharacter();

    private final String GIVEN_INPUT = "aarc";
    private final char EXPECTED_RESULT = 'r';

    @Test
    void v1() {
        var result = testClass.v1(GIVEN_INPUT);
        Assertions.assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    void v2() {
        var result = testClass.v2(GIVEN_INPUT);
        Assertions.assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    void v3() {
        var result = testClass.v3(GIVEN_INPUT);
        Assertions.assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    void v4() {
        var result = testClass.v4(GIVEN_INPUT);
        Assertions.assertEquals(EXPECTED_RESULT, result);
    }
}
