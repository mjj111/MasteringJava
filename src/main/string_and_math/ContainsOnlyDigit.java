package src.main.string_and_math;

//숫자만 포함하는 문자열인지 확인
public class ContainsOnlyDigit {

    public boolean v1(String str) {

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean v2(String str) {
        return str.matches("[0-9]+");
    }

    public boolean v3(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
}
