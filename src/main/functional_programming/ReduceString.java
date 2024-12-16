package src.main.functional_programming;

import java.util.function.Function;
import java.util.stream.Stream;

//문자열에 누적함수 적용
public class ReduceString {

    @SuppressWarnings("unchecked")
    public static Function<String, String> reduceStrings(Function<String, String>... functions) {
        return Stream.of(functions)
                .reduce(Function.identity(), Function::andThen);
    }
}
