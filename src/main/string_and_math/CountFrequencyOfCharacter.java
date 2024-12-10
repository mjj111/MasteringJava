package src.main.string_and_math;

//문자 빈도수 세기
public class CountFrequencyOfCharacter {
    public Long v1(Dto input) {
        Long result = 0L;
        for(char ch : input.str().toCharArray()) {
            if(ch == input.ch()) {
                result += 1;
            }
        }
        return result;
    }

    public Long v2(Dto input) {
        return input.str().chars().filter(ch -> ch == input.ch()).count();
    }

    public  record Dto(String str, Character ch)  {}
}
