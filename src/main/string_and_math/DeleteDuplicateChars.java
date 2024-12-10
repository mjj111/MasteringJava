package src.main.string_and_math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//문자열에서_중복문자_제거
public class DeleteDuplicateChars {

    public String v1(String str) {
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (sb.indexOf(String.valueOf(ch)) == -1) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    public String v2(String str) {
        char[] chArray = str.toCharArray();
        Set<Character> chHashSet = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        for (char c : chArray) {
            if (chHashSet.add(c)) {
                sb.append(c);
            }

        }

        return sb.toString();
    }

    public String v3(String str) {

        return Arrays.stream(str.split(""))
                .distinct()
                .collect(Collectors.joining());
    }

    public String v4(String str) {

        return str.chars()
                .distinct()
                .mapToObj(ch -> String.valueOf((char) ch))
                .collect(Collectors.joining());
    }
}
