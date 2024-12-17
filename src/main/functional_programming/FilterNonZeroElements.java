package src.main.functional_programming;

import java.util.List;

//스트림에 필터 적용
public class FilterNonZeroElements {

    // 0이 아닌숫자들
    public static List<Integer> notZeroNumbers(List<Integer> inputNumbers) {
        return inputNumbers.stream()
                .filter(i -> i != 0)
                .toList();
    }

    // 0이 아니면서 첫 번쨰는 스킵하고 2개로 하여 정렬된 리스트 반환
    public static List<Integer> notZeroAndOneSkipWithSortedNumbers(List<Integer> inputNumbers) {
        return inputNumbers.stream()
                .filter(i -> i != 0)
                .distinct()
                .skip(1)
                .limit(2)
                .sorted()
                .toList();
    }

    // 0초과하며 10미만인 값이면서 짝수인 리스트 반환
    public static List<Integer> NotBigNotSmallEvenNumbers(List<Integer> inputNumbers) {
        return inputNumbers.stream()
                .filter(FilterNonZeroElements::evenBetween0And10)
                .toList();
    }

    private static boolean evenBetween0And10(int value) {
        return value > 0 && value < 10 && value % 2 == 0;
    }
}
