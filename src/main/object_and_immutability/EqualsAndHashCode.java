package src.main.object_and_immutability;

import java.util.List;
import java.util.Objects;

//Equals와_HashCode_오버라이딩_및_동등성_확인
public class EqualsAndHashCode {

    public static class Player {

        private int id;
        private String name;

        public Player(int id, String name) {
            this.id = id;
            this.name = name;
        }

        //id가 같다면 같은 객체로 인식하도록 수정
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Player) {
                if (id == ((Player) obj).id) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id); // 주소값을 갖고 해시코드를 만드는 것이라 아니라, id로 해시코드 만들도록 수정
        }
    }

    public boolean AllSameEqualsCheck(List<Player> players) {
        Player first = players.get(0);
        return players.stream().allMatch(player -> Objects.equals(player, first));
    }

    public boolean AllSameHashCodeCheck(List<Player> players) {
        int firstHashCode = players.get(0).hashCode();
        return players.stream().allMatch(player -> player.hashCode() == firstHashCode);
    }
}
