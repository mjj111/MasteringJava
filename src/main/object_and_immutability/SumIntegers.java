package src.main.object_and_immutability;

import java.util.List;
import java.util.Objects;

//null을 제외한 숫자합
public class SumIntegers {

    public int v1(List<Integer> numbers) {
        if (numbers == null) throw new IllegalArgumentException("List cannot be null");

        int sum = 0;
        for (Integer num : numbers) {
            if (num != null) {
                sum += num;
            }
        }
        return sum;
    }

    public int v2(List<Integer> numbers) {
        if (Objects.isNull(numbers)) throw new IllegalArgumentException("List cannot be null");

        return numbers.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue).sum();
    }
}
