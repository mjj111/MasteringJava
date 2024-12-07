package src.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.string_and_math.CountDuplicateCharacters;

import java.util.Map;

public class CountDuplicateCharactersTest {

    private final String GVIEN_INPUT = "asdqbaa";
    private final Map<Character, Long> EXPECTED_RESULT = Map.of('a',3L,
                                                                    's',1L,
                                                                    'd',1L,
                                                                    'q',1L,
                                                                    'b',1L);

    private final CountDuplicateCharacters testClass = new CountDuplicateCharacters();

    @Test
    void v1() {
        var result = testClass.V1(GVIEN_INPUT);
        Assertions.assertEquals(EXPECTED_RESULT, result);
    }

    @Test
    void v2() {
        var result = testClass.V2(GVIEN_INPUT);
        Assertions.assertEquals(EXPECTED_RESULT, result);
    }
}
