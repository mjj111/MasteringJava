package src.test.auto_tester.startegy;

import java.lang.reflect.Method;

public interface Validator<T, R> {
    void validate(T input, R expectedOutput, Method method, Object instance) throws Exception;
}
