package src.main.object_and_immutability;

import java.util.List;
import java.util.Objects;

//null이 들어갔는지 확인
public class NumbersContainsNulls {

    public boolean v1(List<Integer> numbers) {
        if (numbers == null) {
            return false;
        }

        for (Integer num : numbers) {
            if (num == null) return true;
        }
        return false;
    }

    public boolean v2(List<Integer> numbers) {
        if (Objects.isNull(numbers)) return false;

        return numbers.stream()
                .anyMatch(Objects::isNull);
    }
}
