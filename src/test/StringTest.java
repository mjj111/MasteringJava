package src.test;

import org.junit.jupiter.api.Test;
import src.main.string_and_math.*;
import src.test.auto_tester.AutoTester;

import java.util.Map;

public class StringTest extends AutoTester {

    @Test
    public void 문자_빈도수_세기(){
        var GIVEN_INPUT = new CountFrequencyOfCharacter.Dto("aexxxi", 'i');
        var EXPECTED_OUTPUT = 1L;

        testMethods(CountFrequencyOfCharacter.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 숫자만_포함하는_문자열인지_확인(){
        var GIVEN_RIGHT_INPUT = "123123";
        var GIVEN_WRONG_INPUT = "sdk124125";

        testMethods(ContainsOnlyDigit.class, GIVEN_RIGHT_INPUT, true);
        testMethods(ContainsOnlyDigit.class, GIVEN_WRONG_INPUT, false);
    }

    @Test
    public void 문자열에서_문자_빈도수를_반환(){
        var GIVEN_INPUT = "asdqbaa";
        var EXPECTED_OUTPUT = Map.of('a',3L,
                's',1L,
                'd',1L,
                'q',1L,
                'b',1L);

        testMethods(CountDuplicateCharacters.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 자음과_모음_개수_세기() {
        var GIVEN_INPUT = "aexxxi";
        var EXPECTED_OUTPUT = CountVowelsAndConsonants.Pair.of(3L,3L);

        testMethods(CountVowelsAndConsonants.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 반복되지_않는_첫_번째_문자_찾기() {
        var GIVEN_INPUT = "aarc";
        var EXPECTED_OUTPUT = 'r';

        testMethods(FirstNonRepeatedCharacter.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 문자열_뒤집기() {
        var GIVEN_INPUT = "12441 12441 12441 12441 12441 12441 12441";
        var EXPECTED_OUTPUT = "14421 14421 14421 14421 14421 14421 14421";

        testMethods(ReverseWords.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }
}
