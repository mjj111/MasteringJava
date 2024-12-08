package src.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.string_and_math.CountVowelsAndConsonants;
import src.test.time_tracker.ExecutionTimeTracker;

public class CountVowelsAndConsonantsTest extends ExecutionTimeTracker {
    private final CountVowelsAndConsonants testClass = new CountVowelsAndConsonants();

    private final String GIVEN_TEXT = "aexxxi";
    private final CountVowelsAndConsonants.Pair<Long, Long> EXPECTED_RESULT = CountVowelsAndConsonants.Pair.of(3L,3L);

    @Test
    public void v1() {
        var result = testClass.v1(GIVEN_TEXT);
        Assertions.assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    public void v2() {
        var result = testClass.v2(GIVEN_TEXT);
        Assertions.assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    public void v3() {
        var result = testClass.v3(GIVEN_TEXT);
        Assertions.assertEquals(EXPECTED_RESULT, result);
    }
}
