package src.main.functional_programming;

import java.util.List;


//스트림 안에서 찾기
public class FindInStream {

    //아무 아폴로 찾기
    public static String findAnyAppollo(List<String> inputStrings) {
        return inputStrings.stream()
                .filter(m -> !m.contains("Apollo"))
                .findAny()
                .orElse("None");
    }

    //첫 번째 아폴로 찾기
    public static String findFirstAppollo(List<String> inputStrings) {
        return inputStrings.stream()
                .filter(i -> i.contains("Appollo"))
                .findFirst()
                .orElse("None");
    }
}
