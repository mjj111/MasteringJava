package src.test;

import org.junit.jupiter.api.Test;
import src.main.string_and_math.*;
import src.test.auto_tester.AutoTester;

import java.util.List;
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

    @Test
    public void 구분자로_여러_문자열_합치기() {
        var GIVEN_INPUT = new JoinByDelimiter.Dto( '!', "하하", "반갑소만", "자바 다시 공부하는건 정말 재밌닷.");
        var EXPECTED_OUTPUT = "하하!반갑소만!자바 다시 공부하는건 정말 재밌닷.";

        testMethods(JoinByDelimiter.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 순열_만들기() {
        var GIVEN_INPUT = "반가워";
        var EXPECTED_OUTPUT = List.of("반가워", "반워가", "가반워", "가워반", "워반가", "워가반");

        testMethods(GeneratePermutations.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 팰린드롬_확인() {
        var RIGHT_INPUT = "121";
        var WRONG_INPUT = "12131";

        testMethods(CheckPalindrome.class, RIGHT_INPUT, true);
        testMethods(CheckPalindrome.class, WRONG_INPUT, false);
    }

    @Test
    public void 문자열에서_중복문자_제거() {
        var RIGHT_INPUT = "1221";
        var EXPECTED_OUTPUT = "12";

        testMethods(DeleteDuplicateChars.class, RIGHT_INPUT, EXPECTED_OUTPUT);
    }
}
