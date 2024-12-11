package src.test.auto_tester.startegy;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneralValidator<T, R> implements Validator<T, R> {

    @Override
    public void validate(T input, R expectedOutput, Method method, Object instance) throws Exception {
        var result = method.invoke(instance, input);
        assertEquals(expectedOutput, result);
    }
}
