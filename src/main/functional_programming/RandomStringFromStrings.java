package src.main.functional_programming;

import java.util.List;
import java.util.Random;

//문자열 랜덤 하나추출
public class RandomStringFromStrings {

    public static List<String> extract(List<String> strs) {

        return strs.stream()
                .map(RandomStringFromStrings::selectChar)
                .toList();
    }

    public static String selectChar(String str) {
        Random rnd = new Random();
        int nr = rnd.nextInt(str.length());
        String chAsStr = String.valueOf(str.charAt(nr));

        return chAsStr;
    }
}