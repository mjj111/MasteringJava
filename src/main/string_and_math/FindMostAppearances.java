package src.main.string_and_math;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//가장 높은 빈도의 문자 반환
public class FindMostAppearances {

    public char v1(String str) {
        Map<Character, Integer> counter = new HashMap<>();
        char[] chStr = str.toCharArray();
        for (int i = 0; i < chStr.length; i++) {

            char currentCh = chStr[i];
            if (!Character.isWhitespace(currentCh)) {
                Integer noCh = counter.get(currentCh);

                if (noCh == null) {
                    counter.put(currentCh, 1);
                } else {
                    counter.put(currentCh, ++noCh);
                }
            }
        }

        int maxOccurrences = Collections.max(counter.values());

        char maxCharacter = Character.MIN_VALUE;
        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == maxOccurrences) {
                maxCharacter = entry.getKey();
            }
        }

        return maxCharacter;
    }

    public char v2(String str) {
        int maxOccurrences = -1;
        char maxCharacter = Character.MIN_VALUE;

        char[] chStr = str.toCharArray();
        int[] asciiCodes = new int[255];

        for (int i = 0; i < chStr.length; i++) {
            char currentCh = chStr[i];
            if (Character.isWhitespace(currentCh)) continue;

            int code = currentCh;
            asciiCodes[code]++;

            if (asciiCodes[code] > maxOccurrences) {
                maxOccurrences = asciiCodes[code];
                maxCharacter = currentCh;
            }
        }

        return maxCharacter;
    }

    public char v3(String str) {
        return str.chars()
                .filter(c -> !Character.isWhitespace(c))
                .boxed()
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> (char) e.getKey().intValue())
                .orElse(Character.MIN_VALUE);
    }
}
