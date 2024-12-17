package src.test;

import org.junit.jupiter.api.Test;
import src.main.functional_programming.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void 숫자0이_없는_리스트(){
        var GIVEN_INPUT = Arrays.asList(1, 0, 3, 2);
        var EXPECTED_INPUT = Arrays.asList(1,3,2);

        var result = FilterNonZeroElements.notZeroNumbers(GIVEN_INPUT);

        assertEquals(EXPECTED_INPUT, result);
    }

    @Test
    public void 숫자0이_아니면서_첫_번쨰는_스킵하고_2개_요소를가진_정렬된_리스트_반환(){
        var GIVEN_INPUT = Arrays.asList(1, 0, 3, 2,10,4);
        var EXPECTED_INPUT = Arrays.asList(2,3);

        var result = FilterNonZeroElements.notZeroAndOneSkipWithSortedNumbers(GIVEN_INPUT);

        assertEquals(EXPECTED_INPUT, result);
    }

    @Test
    public void 숫자0_초과하며_10미만인_값이면서_짝수인_리스트(){
        var GIVEN_INPUT = Arrays.asList(1, 0, 3, 2, 122, 10, 4);
        var EXPECTED_INPUT = Arrays.asList(2, 4);

        var result = FilterNonZeroElements.NotBigNotSmallEvenNumbers(GIVEN_INPUT);

        assertEquals(EXPECTED_INPUT, result);
    }

    @Test
    public void 아무_Appollo찾기(){
        var GIVEN_INPUT = List.of("Appollo1","Appollo2","2","hi");

        var result = FindInStream.findAnyAppollo(GIVEN_INPUT);

        assertTrue(result.contains("Appollo"));
    }

    @Test
    public void 첫_번째_Appollo_찾기(){
        var GIVEN_INPUT = List.of("221", "Appollo1","Appollo2","2","hi");

        var result = FindInStream.findFirstAppollo(GIVEN_INPUT);

        assertEquals("Appollo1", result);
    }
}
