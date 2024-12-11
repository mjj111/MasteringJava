package src.test.auto_tester.startegy;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArrayValidator<T, R> implements Validator<T, R> {

    @Override
    public void validate(T input, R expectedOutput, Method method, Object instance) throws Exception {
        var result = method.invoke(instance, input);
        assertArrayEquals((Object[]) result, (Object[]) expectedOutput);
    }
}
