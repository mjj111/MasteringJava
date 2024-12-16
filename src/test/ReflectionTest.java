package src.test;

import org.junit.jupiter.api.Test;
import src.main.reflection.FindByAnnotation;
import src.main.reflection.InspectingPackages;
import src.main.reflection.InstancingViaConstructor;
import src.test.auto_tester.AutoTester;

import java.util.Optional;

public class ReflectionTest extends AutoTester {
    @Test
    public void 패키지경로_가져오기() {
        var GIVEN_INPUT = "src.main.collections_and_arrays.ThreadSafeMap";
        var EXPECTED_OUTPUT = "src.main.collections_and_arrays";

       testMethods(InspectingPackages.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 인스턴스_생성하기_NotNull(){
        var GIVEN_INPUT = Optional.of(new InstancingViaConstructor.Dto(1, "김명준"));
        var EXPECTED_OUTPUT = new InstancingViaConstructor.TestClass(1, "김명준");

        testMethods(InstancingViaConstructor.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 인스턴스_생성하기_Null_기본생성자(){
        var GIVEN_INPUT = Optional.empty();
        var EXPECTED_OUTPUT = new InstancingViaConstructor.TestClass(2, "DEFAULT");

        testMethods(InstancingViaConstructor.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 애너테이션_기반_클래스_조회() {
        var GIVEN_INPUT =FindByAnnotation.ToFind.class;
        var EXPECTED_OUTPUT = FindByAnnotation.TestClass.class;

        testMethods(FindByAnnotation.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

}
