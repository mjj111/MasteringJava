package src.test.auto_tester;


import src.test.auto_tester.startegy.ArrayValidator;
import src.test.auto_tester.startegy.ExceptionValidator;
import src.test.auto_tester.startegy.GeneralValidator;
import src.test.auto_tester.startegy.Validator;

import java.lang.reflect.Method;

public class ResultValidator {

    public <T, R> void check(T input, R expectedOutput, Method method, Object instance) throws Exception {
        Validator<T, R> strategy = selectStrategy(expectedOutput);
        strategy.validate(input, expectedOutput, method, instance);
    }

    private <T, R> Validator<T, R> selectStrategy(R expectedOutput) {
        if (expectedOutput instanceof Class<?> && Throwable.class.isAssignableFrom((Class<?>) expectedOutput)) {
            return (Validator<T, R>) new ExceptionValidator<>();
        } else if (expectedOutput instanceof Object[]) {
            return new ArrayValidator<>();
        } else {
            return new GeneralValidator<>();
        }
    }
}
