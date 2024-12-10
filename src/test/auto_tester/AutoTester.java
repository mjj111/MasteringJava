package src.test.auto_tester;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AutoTester {

    private final ExecutionTimeTracker tracker = new ExecutionTimeTracker();

    public <T, R> void testMethods(Class<?> clazz, T input, R expectedOutput) {
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();

            for (Method method : clazz.getDeclaredMethods()) {
                if (isUnintended(method)) continue;

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

    private static boolean isUnintended(Method method) {
        if (method.getName().contains("lambda") || java.lang.reflect.Modifier.isPrivate(method.getModifiers())) {
            return true;
        }
        return false;
    }
}
