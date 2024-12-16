package src.test;

import org.junit.jupiter.api.Test;
import src.main.functional_programming.FindFirstAndLast;
import src.main.functional_programming.ReduceString;
import src.main.functional_programming.ReplaceList;
import src.main.functional_programming.RandomStringFromStrings;

import java.util.Arrays;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FunctionalProgrammingTest {

    @Test
    void 리스트의_각_문자열에_함수_적용() {
        var GIVEN_INPUT = Arrays.asList("김명준은 손가락이 10개다.", "1 2 반갑습니다.");
        var EXPECTED_OUTPUT = Arrays.asList("김명준은손가락이10개다.", "12반갑습니다.");

        var result = ReplaceList.replace(GIVEN_INPUT, s -> s.replaceAll("\\s", ""));
        assertEquals(EXPECTED_OUTPUT, result);
    }

    @Test
    void 문자열에_누적함수_적용() {
        Function<String, String> f1 = String::toUpperCase;
        Function<String, String> f2 = s-> s.concat(" DONE");

        Function<String, String> f = ReduceString.reduceStrings(f1, f2);

        assertEquals("TEST DONE", f.apply("test"));
    }

    @Test
    void 첫_번째와_마지막_추출(){
        var GIVEN_INPUT = "Lambda";
        var EXPECTED_OUTPUT = FindFirstAndLast.firstAndLastChar.apply(GIVEN_INPUT);

        assertEquals("La", EXPECTED_OUTPUT);
    }

    @Test
    public void 문자열_랜덤_하나추출() {
        var GIVEN_INPUT = Arrays.asList("Temporary", "Random", "Text");

        var result = RandomStringFromStrings.extract(GIVEN_INPUT);

        assertEquals(GIVEN_INPUT.size(), result.size());
        assertNotEquals(GIVEN_INPUT, result);
    }
}
