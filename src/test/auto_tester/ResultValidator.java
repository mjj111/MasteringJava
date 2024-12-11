package src.test.auto_tester;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultValidator {

    public <T, R> void check(T input, R expectedOutput, Method method, Object instance) throws IllegalAccessException, InvocationTargetException {
        var result = method.invoke(instance, input);

        if (result instanceof Object[]) {
            assertArrayEquals((Object[]) result, (Object[]) expectedOutput);
        }else {
            assertEquals(expectedOutput, result);
        }
    }
}
