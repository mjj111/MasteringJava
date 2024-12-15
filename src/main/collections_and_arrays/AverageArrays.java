package src.main.collections_and_arrays;

import java.util.Arrays;

//배열의 평균값 계산
public class AverageArrays {

    public double v1(int[] arr) {
        double sum = 0;
        for (int elem : arr) {
            sum += elem;
        }

        return sum / arr.length;
    }

    public double v2(int[] arr) {
        return Arrays.stream(arr).average().orElse(0.0);
    }

    public double v3(int[] arr) {
        return Arrays.stream(arr)
                .reduce(0, Integer::sum) / (double) arr.length;
    }
}
