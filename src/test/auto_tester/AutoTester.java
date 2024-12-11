package src.test.auto_tester;

import java.lang.reflect.Method;

public class AutoTester {

    private final ExecutionTimeTracker tracker = new ExecutionTimeTracker();
    private final ResultValidator validator = new ResultValidator();

    public <T, R> void testMethods(Class<?> clazz, T input, R expectedOutput) {
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();

            for (Method method : clazz.getDeclaredMethods()) {
                if (isUnintended(method)) continue;

                System.out.print(method.getName() + " ");
                tracker.trackExecutionTime(() -> {
                    validator.check(input, expectedOutput, method, instance);
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
