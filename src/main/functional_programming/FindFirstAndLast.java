package src.main.functional_programming;

import java.util.function.Function;

//첫 번째와 마지막 추출
public class FindFirstAndLast {
    public static final Function<String, String> firstAndLastChar
            = s -> s.charAt(0) + String.valueOf(s.charAt(s.length() - 1));
}
