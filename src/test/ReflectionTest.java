package src.test;

import org.junit.jupiter.api.Test;
import src.main.reflection.InspectingPackages;
import src.test.auto_tester.AutoTester;

public class ReflectionTest extends AutoTester {
    @Test
    public void testReflection() {
        var GIVEN_INPUT = "src.main.collections_and_arrays.ThreadSafeMap";
        var EXPECTED_OUTPUT = "src.main.collections_and_arrays";

       testMethods(InspectingPackages.class, GIVEN_INPUT, EXPECTED_OUTPUT);
    }
}
