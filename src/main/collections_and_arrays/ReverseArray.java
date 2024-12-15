package src.main.collections_and_arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

//배열 뒤집기
public class ReverseArray {

    public <T> T[] v1(T[] input) {
        T[] reversed = input.clone();
        for (int leftHead = 0, rightHead = reversed.length - 1;
             leftHead < rightHead; leftHead++, rightHead--) {

            T elem = reversed[leftHead];
            reversed[leftHead] = reversed[rightHead];
            reversed[rightHead] = elem;
        }
        return reversed;
    }

    public <T> T[] v2(T[] input) {
        List<T> list = Arrays.asList(Arrays.copyOf(input, input.length));
        Collections.reverse(list);
        return list.toArray(input.clone());
    }


    public <T> T[] v3(T[] input) {
        T[] reversed = input.clone();
        IntStream.range(0, input.length)
                .forEach(i -> reversed[i] = input[input.length - 1 - i]);
        return reversed;
    }

    public <T> T[] v4(T[] input) {
        T[] reversed = input.clone();
        reverseRecursive(reversed, 0, input.length - 1);
        return reversed;
    }

    private <T> void reverseRecursive(T[] arr, int left, int right) {
        if (left >= right) return;
        T temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        reverseRecursive(arr, left + 1, right - 1);
    }

    public <T> T[] v5(T[] input) {
        T[] reversed = input.clone();
        IntStream.range(0, input.length / 2)
                .forEach(i -> swapElements(reversed, i, input.length - 1 - i));
        return reversed;
    }

    private <T> void swapElements(T[] array, int leftIndex, int rightIndex) {
        T temp = array[leftIndex];
        array[leftIndex] = array[rightIndex];
        array[rightIndex] = temp;
    }
}
