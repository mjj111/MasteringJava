package src.main.collections_and_arrays;

import java.util.Arrays;

//배열에서 최대값 계산
public class MaxArrays {
    public int v1(int[] arr) {
        int max = arr[0];

        for (int elem : arr) {
            if (elem > max) {
                max = elem;
            }
        }

        return max;
    }

    public int v2(int[] arr) {
        int max = arr[0];

        for (int elem : arr) {
            max = Math.max(max, elem);
        }

        return max;
    }

    public int v3(int[] arr) {
        return Arrays.stream(arr).max().orElse(arr[0]);
    }
}
