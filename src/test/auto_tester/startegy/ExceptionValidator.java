package src.test.auto_tester.startegy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExceptionValidator<T> implements Validator<T, Class<? extends Throwable>> {

    @Override
    public void validate(T input, Class<? extends Throwable> expectedOutput, Method method, Object instance) throws Exception {
        try {
            method.invoke(instance, input);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            assertTrue(expectedOutput.isInstance(cause));
            return;
        }
        throw new AssertionError("Expected exception: " + expectedOutput + ", but no exception was thrown");
    }
}
