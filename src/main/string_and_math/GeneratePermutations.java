package src.main.string_and_math;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//순열 만들기
public class GeneratePermutations {

    public List<String> v1(String str) {
        if (str == null || str.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> permutations = new ArrayList<>();
        permute("", str, permutations);
        return permutations;
    }

    private void permute(String prefix, String remaining, List<String> result) {
        if (remaining.isEmpty()){
            result.add(prefix);
            return;
        }

        for (int i = 0; i < remaining.length(); i++) {
            permute(prefix + remaining.charAt(i), remaining.substring(0, i) + remaining.substring(i + 1), result);
        }
    }


    // 순열을 병렬 스트림으로 생성
    public List<String> v2(String str) {
        if (str == null || str.isEmpty()) return Collections.emptyList();

        return generateStream("", str).toList();
    }

    private Stream<String> generateStream(String prefix, String remaining) {
        if (remaining.isEmpty()) return Stream.of(prefix);

        return IntStream.range(0, remaining.length())
                .parallel()
                .boxed()
                .flatMap(i -> generateStream(prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i + 1)));
    }
}
