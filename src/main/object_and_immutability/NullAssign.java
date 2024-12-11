package src.main.object_and_immutability;

import java.util.Objects;

//입력값이 null_이라면 default값 전달
public class NullAssign {

    public int v1(Integer input) {
        if(input == null) return 15;
        return input;
    }

    public int v2(Integer input) {
        return Objects.requireNonNullElse(input, 15);
    }
}
