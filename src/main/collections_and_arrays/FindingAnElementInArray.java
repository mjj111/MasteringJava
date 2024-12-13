package src.main.collections_and_arrays;

import java.util.Arrays;
import java.util.Objects;

//배열에서 특정 객체 유무 확인
public class FindingAnElementInArray {

    public <T> boolean v1(Dto<T> dto) {
        for (T elem : dto.array()) {
            if (elem.equals(dto.toFind())) {
                return true;
            }
        }
        return false;
    }

    public <T> boolean v2(Dto<T> dto) {
        Arrays.sort(dto.array());
        int index = Arrays.binarySearch(dto.array(), dto.toFind());
        return index >= 0;
    }

    public <T> boolean v3(Dto<T> dto) {
        return Arrays.asList(dto.array()).contains(dto.toFind());
    }

    public record Dto<T>(T toFind, T... array) {}


    public static class Fruit implements Comparable<Fruit> {

        private final String type;
        private final int weight;

        public Fruit(String type, int weight) {
            this.type = type;
            this.weight = weight;
        }

        public String getType() {
            return type;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, weight);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Fruit other)) {
                return false;
            }
            return this.weight == other.weight && Objects.equals(this.type, other.type);
        }

        @Override
        public int compareTo(Fruit other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
}
