package src.main.string_and_math;

import java.util.stream.IntStream;

//팰린드롬 확인
public class CheckPalindrome {

    public boolean v1(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (right > left) {

            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public boolean v2(String str) {
        int n = str.length();

        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n - i - 1)) {
                return false;
            }
        }

        return true;
    }

    public boolean v3(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    public boolean v4(String str) {
        return IntStream.range(0, str.length() / 2)
                .noneMatch(p -> str.charAt(p) != str.charAt(str.length() - p - 1));
    }
}
