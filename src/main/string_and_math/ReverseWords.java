package src.main.string_and_math;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

// 문자열 뒤집기
public class ReverseWords {
    private static final Pattern PATTERN = Pattern.compile(" +");
    private static final String WHITESPACE = " ";

    public String v1(String str) {
        String[] words = str.split(WHITESPACE);
        StringBuilder reversedString = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            StringBuilder reverseWord = new StringBuilder();

            for (int j = word.length() - 1; j >= 0; j--) {
                reverseWord.append(word.charAt(j));
            }

            reversedString.append(reverseWord);
            if (i != words.length - 1) {
                reversedString.append(WHITESPACE);
            }
        }

        return reversedString.toString();
    }

    public String v2(String str) {
        return PATTERN.splitAsStream(str)
                .map(w -> new StringBuilder(w).reverse())
                .collect(Collectors.joining(WHITESPACE));
    }

    public String v3(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}