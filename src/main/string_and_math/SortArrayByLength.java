package src.main.string_and_math;

import java.util.Arrays;
import java.util.Comparator;

//문자열 배열을 길이 순으로 오름차순 정렬
public class SortArrayByLength {

    public String[] v1(String[] strs) {
        String[] temp = strs;
        Arrays.sort(temp, Comparator.comparingInt(String::length));
        return temp;
    }

    public String[] v2(String[] strs) {
        return Arrays.stream(strs)
                .sorted(Comparator.comparingInt(String::length))
                .toArray(String[]::new);
    }
}
