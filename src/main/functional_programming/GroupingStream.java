package src.main.functional_programming;

import java.util.*;

import static java.util.stream.Collectors.*;

//스트림에 그룹화 적용
public class GroupingStream {

    //타입에_따른_그룹화_리스트
    public static Map<String, List<Melon>> byTypeList(List<Melon> melons) {
        return melons.stream()
                .collect(groupingBy(Melon::getType));
    }

    //무게에_따른_그룹화_리스트
    public static Map<Integer, List<Melon>> byWeightInList (List<Melon> melons){
        return melons.stream()
                .collect(groupingBy(Melon::getWeight));
    }

    //타입에_따른_그룹화_셋
    public static Map<String, Set<Melon>> byTypeInSet(List<Melon> melons) {
        return melons.stream()
                .collect(groupingBy(Melon::getType, toSet()));
    }

    //설탕과_무게에_따른_그룹화_맵
    public static  Map<Sugar, TreeMap<Integer, Set<String>>> bySugarAndWeight(List<Melon> melons) {
        return melons.stream()
                .collect(groupingBy(Melon::getSugar,
                        groupingBy(Melon::getWeight, TreeMap::new,
                                mapping(Melon::getType, toSet()))));
    }
    public  enum Sugar {
        LOW, MEDIUM, HIGH, UNKONWN
    }

    public static class Melon {

        private final String type;
        private final int weight;
        private final Sugar sugar;

        public Melon(String type, int weight) {
            this.type = type;
            this.weight = weight;
            this.sugar = Sugar.UNKONWN;
        }

        public Melon(String type, int weight, Sugar sugar) {
            this.type = type;
            this.weight = weight;
            this.sugar = sugar;
        }

        public String getType() {
            return type;
        }

        public int getWeight() {
            return weight;
        }

        public Sugar getSugar() {
            return sugar;
        }

        @Override
        public String toString() {
            return type + "(" + weight + "g)" + (sugar != Sugar.UNKONWN ? " " + sugar : "");
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 53 * hash + Objects.hashCode(this.type);
            hash = 53 * hash + this.weight;
            hash = 53 * hash + Objects.hashCode(this.sugar);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Melon other = (Melon) obj;
            if (this.weight != other.weight) {
                return false;
            }
            if (!Objects.equals(this.type, other.type)) {
                return false;
            }
            if (!Objects.equals(this.sugar, other.sugar)) {
                return false;
            }
            return true;
        }

    }
}
