package src.main.string_and_math;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// 반복되지 않는 첫 번째 문자 찾기
public class FirstNonRepeatedCharacter {

    private static int EXTENDED_ASCII_CODES = 255;

    public char v1(String str) {

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            int count = 0;
            for (int j = 0; j < str.length(); j++) {
                if (ch == str.charAt(j) && i != j) {
                    count++;
                    break;
                }
            }

            if (count == 0) return ch;

        }

        return Character.MIN_VALUE;
    }

    public char v2(String str) {

        int[] flags = new int[EXTENDED_ASCII_CODES];
        Arrays.fill(flags, -1);

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);
            if (flags[ch] == -1) {
                flags[ch] = i;
            } else {
                flags[ch] = -2;
            }
        }

        int position = Integer.MAX_VALUE;
        for (int i = 0; i < EXTENDED_ASCII_CODES; i++) {
            if (flags[i] >= 0) {
                position = Math.min(position, flags[i]);
            }
        }

        return position == Integer.MAX_VALUE ? Character.MIN_VALUE : str.charAt(position);
    }

    public char v3(String str) {

        Map<Character, Integer> chars = new LinkedHashMap<>();

        for (char ch : str.toCharArray()) {
            chars.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }

        for (Map.Entry<Character, Integer> entry : chars.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return Character.MIN_VALUE;
    }

    public char v4(String str) {

        Map<Integer, Long> chs = str.chars()
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(),
                        LinkedHashMap::new, Collectors.counting()));

        return (char) (int) chs.entrySet().stream()
                .filter(e -> e.getValue() == 1L)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse((int) Character.MIN_VALUE);
    }
}
