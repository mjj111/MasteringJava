package src.main.string_and_math;
import java.util.*;
import java.util.stream.Collectors;

// 문자열에서 문자 수를 반환
public class CountDuplicateCharacters {

    public Map<Character, Long> V1(String str) {

        Map<Character, Long> result = new HashMap<>();
        for (char c : str.toCharArray()) {
            result.compute(c, (key, value) -> (value == null) ? 1 : ++value);
        }

        return result;
    }

    public Map<Character, Long> V2(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }
}
