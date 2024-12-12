package src.main.object_and_immutability;


import java.util.Objects;
import java.util.stream.IntStream;

//인덱스 범위 확인 및 예외 던지기
public class CheckIndexOutOfRange {
    private final int START = 0;
    private final int END = 100;
    private final int[] numbers = IntStream.rangeClosed(START, END).toArray();

    public int v1(int index) {
        if(index < START || index > END) throw new IndexOutOfBoundsException();
        return numbers[index];
    }

    public int v2(int index) {
        return Objects.checkIndex(index, END);
    }
}
