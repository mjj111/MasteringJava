package src.test.auto_tester;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AutoTester {

    private final ExecutionTimeTracker tracker = new ExecutionTimeTracker();

    public <T, R> void testMethods(Class<?> clazz, T input, R expectedOutput) {
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().contains("lambda")) continue;

                System.out.print(method.getName() + " ");
                tracker.trackExecutionTime(() -> {
                    var result = method.invoke(instance, input);
                    assertEquals(expectedOutput, result);
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Test Failed: " + clazz.getSimpleName());
        }
    }
}
