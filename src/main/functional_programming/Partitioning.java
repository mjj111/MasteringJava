package src.main.functional_programming;

import java.util.*;

import static java.util.stream.Collectors.*;
import static src.main.functional_programming.GroupingStream.Melon;

//스트림 데이터에 기준에 따른, 파티셔닝
public class Partitioning {

    //무게별로 파티셔닝하여 리스트 반환
    public static Map<Boolean, List<Melon>> partitionByWeight(List<Melon> melons, int weight) {
        return melons.stream()
                .collect(partitioningBy(m -> m.getWeight() > weight));
    }

    //무게별로 파티셔닝하여 셋 반환
    public static Map<Boolean, Set<Melon>> partitionByWeightToSet(List<Melon> melons, int weight) {
        return melons.stream()
                .collect(partitioningBy(m -> m.getWeight() > weight, toSet()));
    }

    //무게별로 파티셔닝하여 개수별로 맵 반환
    public static Map<Boolean, Long> partitionByWeightAndCount(List<Melon> melons, int weight) {
        return melons.stream()
                .collect(partitioningBy(m -> m.getWeight() > weight, counting()));
    }

    //무게별로 파티셔닝하고 최대무게 반환
    public static Map<Boolean, GroupingStream.Melon> partitionByWeightAndMax(List<Melon> melons, int weight) {
        return melons.stream()
                .collect(partitioningBy(
                        m -> m.getWeight() > weight,
                        collectingAndThen(maxBy(Comparator.comparingInt(Melon::getWeight)), Optional::get)
                ));
    }
}
