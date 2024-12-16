package src.main.functional_programming;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

//리스트의 각 문자열에 함수적용
public class ReplaceList {

    public static List<String> replace(List<String> list, Function<String, String> replacer) {
        if (list == null || replacer == null) {
            throw new IllegalArgumentException("List/replacer cannot be null");
        }

        List<String> result = new ArrayList<>();
        for (String s : list) {
            result.add(replacer.apply(s));
        }

        return result;
    }
}