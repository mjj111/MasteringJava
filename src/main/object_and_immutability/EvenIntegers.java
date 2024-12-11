package src.main.object_and_immutability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EvenIntegers {

    public List<Integer> v1(List<Integer> numbers) {
        if (numbers == null) return Collections.emptyList();

        List<Integer> result = new ArrayList<>();
        for (Integer num : numbers) {
            if (num != null && num % 2 == 0) {
                result.add(num);
            }
        }
        return result;
    }

    public List<Integer> v2(List<Integer> numbers) {
        if (numbers == null) return Collections.EMPTY_LIST;

        return numbers.stream()
                .filter(Objects::nonNull)
                .filter(i -> i % 2 == 0)
                .toList();
    }
}
