package src.main.string_and_math;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//문자열에서 특정 문자열 등장 횟수 확인
public class CountingStringInString {
    public int v1(Dto dto) {
        Pattern pattern = Pattern.compile(Pattern.quote(dto.toFind));
        Matcher matcher = pattern.matcher(dto.string);

        int position = 0;
        int count = 0;
        while (matcher.find(position)) {
            position = matcher.start() + 1;
            count++;
        }

        return count;
    }

    public int v2WithKMP(Dto dto) {


        int[] lps = buildLPS(dto.toFind);
        int givenLength = dto.string.length();
        int findLength = dto.toFind.length();

        int givenIdx = 0;
        int findIdx = 0;
        int count = 0;

        while (givenIdx < givenLength) {
            if (dto.string.charAt(givenIdx) == dto.toFind.charAt(findIdx)) {
                givenIdx++;
                findIdx++;
            }

            if (findIdx == findLength) {
                count++;
                findIdx = lps[findIdx - 1];
                continue;
            }

            if (givenIdx < givenLength && dto.string.charAt(givenIdx) != dto.toFind.charAt(findIdx)) {
                if (findIdx != 0) {
                    findIdx = lps[findIdx - 1];
                } else {
                    givenIdx++;
                }
            }
        }

        return count;
    }

    // LPS 배열 생성
    private int[] buildLPS(String toFind) {
        int m = toFind.length();
        int[] lps = new int[m];
        int length = 0;
        int i = 1;

        while (i < m) {
            if (toFind.charAt(i) == toFind.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public record Dto (String string, String toFind) { }
}
