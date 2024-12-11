package src.main.object_and_immutability;


import java.util.Objects;

//null 참조검사하여 예외던지기
public class ThrowNullException {
    public void v1(Integer input) {
        if(input == null) throw new NullPointerException();
    }

    public void v2(Integer input) {
        Objects.requireNonNull(input);
    }
}
