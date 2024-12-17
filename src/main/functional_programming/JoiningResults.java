package src.main.functional_programming;

import java.util.List;
import java.util.stream.Collectors;

//결과 조인하기
public class JoiningResults {

    //유일한 숫자만을 모아서 하나의 String으로 반환
    public static String joinDistinctNumberString(List<String> inputStrings) {
        return inputStrings.stream()
                .distinct()
                .sorted()
                .collect(Collectors.joining(","));
    }
}
