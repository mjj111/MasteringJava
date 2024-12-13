package src.test;

import org.junit.jupiter.api.Test;
import src.main.object_and_immutability.*;
import src.test.auto_tester.AutoTester;

import java.util.Arrays;
import java.util.List;

public class ObjectTest extends AutoTester {

    @Test
    public void Null을_제외한_숫자합() {
        var GIVEN_INPUT = Arrays.asList(1, 2, null, 4);
        var EXPECTED_OUTPUT = 7;

        testMethods(SumIntegers.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void null이_들어갔는지_확인() {
        var GIVEN_INPUT = Arrays.asList(1, 2, null, 4);
        var EXPECTED_OUTPUT = true;

        testMethods(NumbersContainsNulls.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }


    @Test
    public void null_을_제외한_짝수들_반환() {
        var GIVEN_INPUT = Arrays.asList(1, 2, 3, null, 4);
        var EXPECTED_OUTPUT = Arrays.asList(2, 4);

        testMethods(EvenIntegers.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 입력값이_null_이라면_default값_전달() {
        Integer GIVEN_INPUT = null;
        var EXPECTED_OUTPUT = 15;

        testMethods(NullAssign.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void null_참조검사하여_예외던지기() {
        Integer GIVEN_INPUT = null;
        var EXPECTED_OUTPUT = NullPointerException.class;

        testMethods(ThrowNullException.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 인덱스_범위_확인_및_예외_던지기() {
        var GIVEN_INPUT = -1;
        var EXPECTED_OUTPUT = IndexOutOfBoundsException.class;

        testMethods(CheckIndexOutOfRange.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void Equals와_HashCode_오버라이딩_및_동등성_확인() {
        var GIVEN_INPUT = List.of(new EqualsAndHashCode.Player(1,"김명준"), new EqualsAndHashCode.Player(1, "김형준"));
        var EXPECTED_OUTPUT = true;

        testMethods(EqualsAndHashCode.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 객체_깊은_복사() {
        var GIVEN_INPUT = new CloningObject.DeepCopyClass("김명준");
        var EXPECTED_OUTPUT = new CloningObject.DeepCopyClass("김명준");

        testMethods(CloningObject.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

}
