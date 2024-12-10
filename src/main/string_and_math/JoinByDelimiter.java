package src.main.string_and_math;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

//구분자로 여러 문자열 합치기
public class JoinByDelimiter {

    public static String v1(Dto dto) {
        StringBuilder result = new StringBuilder();

        int i = 0;
        for (i = 0; i < dto.strs.length - 1; i++) {
            result.append(dto.strs[i]).append(dto.delimiter);
        }

        result.append(dto.strs[i]);

        return result.toString();
    }

    public static String v2(Dto dto) {

        return Arrays.stream(dto.strs)
                .collect(Collectors.joining(String.valueOf(dto.delimiter)));
    }

    public static String v3(Dto dto) {

        StringJoiner joiner = new StringJoiner(String.valueOf(dto.delimiter));

        for (String arg : dto.strs) {
            joiner.add(arg);
        }

        return joiner.toString();
    }
    public record Dto(char delimiter, String... strs) {}
}
