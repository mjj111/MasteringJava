package src.main.string_and_math;

import java.util.stream.Collectors;

//문자열에서 특정문자 삭제
public class RemoveCharacter {
    public String v1(Dto dto) {

        StringBuilder sb = new StringBuilder();
        char[] chArray = dto.str.toCharArray();
        for (char c : chArray) {
            if (c != dto.ch) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public String v2(Dto dto) {
        return dto.str.replaceAll(String.valueOf(dto.ch), "");
    }

    public String v3(Dto dto) {

        return dto.str.chars()
                .filter(c -> c != dto.ch)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }

    public record Dto(String str, char ch) {}
}
