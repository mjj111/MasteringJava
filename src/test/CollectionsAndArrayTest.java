package src.test;

import org.junit.jupiter.api.Test;
import src.main.collections_and_arrays.FindingAnElementInArray;
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
}
