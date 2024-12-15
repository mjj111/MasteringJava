package src.test;

import org.junit.jupiter.api.Test;
import src.main.collections_and_arrays.AverageArrays;
import src.main.collections_and_arrays.FindingAnElementInArray;
import src.main.collections_and_arrays.MaxArrays;
import src.test.auto_tester.AutoTester;

public class CollectionsAndArrayTest extends AutoTester {

    @Test
    public void 배열에서_특정_객체_유무_확인() {
        var fruits = new FindingAnElementInArray.Fruit[]{
                new FindingAnElementInArray.Fruit("멜론", 2),
                new FindingAnElementInArray.Fruit("수박", 3)
        };
        var toFind = new FindingAnElementInArray.Fruit("멜론", 2);

        var GIVEN_INPUT = new FindingAnElementInArray.Dto<>(toFind, fruits);
        var EXPECTED_OUTPUT = true;

        testMethods(FindingAnElementInArray.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 배열의_평균값_계산(){
        var GIVEN_INPUT = new int[]{1,2,3,4,5};
        var EXPECTED_OUTPUT = 3D;

        testMethods(AverageArrays.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }

    @Test
    public void 배열의_최대값_계산(){
        var GIVEN_INPUT = new int[]{1,2,3,4,5};
        var EXPECTED_OUTPUT = 5;

        testMethods(MaxArrays.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }
}
