package string_and_math;
import java.util.*;
import java.util.stream.Collectors;

// 문자열에서 문자 수를 반환
class CountDuplicateCharacters {

    public static void main(String[] args) {
        String inputText = "aabbbcccc";
    }

    public static Map<Character, Integer> V1(String str) {

        Map<Character, Integer> result = new HashMap<>();

        for (char c : str.toCharArray()) {
            result.compute(c, (key, value) -> (value == null) ? 1 : ++value);
        }

        return result;
    }

    public static Map<Character, Long> V2(String str) {
        return str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    public static Map<String, Long> V3(String str) {
        return str.codePoints()
                .mapToObj(c -> String.valueOf(Character.toChars(c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }
}
