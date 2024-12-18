package src.test;

import org.junit.jupiter.api.Test;
import src.main.functional_programming.*;

import java.util.*;
import java.util.function.Function;
import src.main.functional_programming.GroupingStream.Melon;
import src.main.functional_programming.GroupingStream.Sugar;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionalProgrammingTest {

    @Test
    void 리스트의_각_문자열에_함수_적용() {
        var GIVEN_INPUT = Arrays.asList("김명준은 손가락이 10개다.", "1 2 반갑습니다.");
        var EXPECTED_OUTPUT = Arrays.asList("김명준은손가락이10개다.", "12반갑습니다.");

        var result = ReplaceList.replace(GIVEN_INPUT, s -> s.replaceAll("\\s", ""));
        assertEquals(EXPECTED_OUTPUT, result);
    }

    @Test
    void 문자열에_누적함수_적용() {
        Function<String, String> f1 = String::toUpperCase;
        Function<String, String> f2 = s-> s.concat(" DONE");

        Function<String, String> f = ReduceString.reduceStrings(f1, f2);

        assertEquals("TEST DONE", f.apply("test"));
    }

    @Test
    void 첫_번째와_마지막_추출(){
        var GIVEN_INPUT = "Lambda";
        var EXPECTED_OUTPUT = FindFirstAndLast.firstAndLastChar.apply(GIVEN_INPUT);

        assertEquals("La", EXPECTED_OUTPUT);
    }

    @Test
    public void 문자열_랜덤_하나추출() {
        var GIVEN_INPUT = Arrays.asList("Temporary", "Random", "Text");

        var result = RandomStringFromStrings.extract(GIVEN_INPUT);

        assertEquals(GIVEN_INPUT.size(), result.size());
        assertNotEquals(GIVEN_INPUT, result);
    }

    @Test
    public void 숫자0이_없는_리스트(){
        var GIVEN_INPUT = Arrays.asList(1, 0, 3, 2);
        var EXPECTED_INPUT = Arrays.asList(1,3,2);

        var result = FilterNonZeroElements.notZeroNumbers(GIVEN_INPUT);

        assertEquals(EXPECTED_INPUT, result);
    }

    @Test
    public void 숫자0이_아니면서_첫_번쨰는_스킵하고_2개_요소를가진_정렬된_리스트_반환(){
        var GIVEN_INPUT = Arrays.asList(1, 0, 3, 2,10,4);
        var EXPECTED_INPUT = Arrays.asList(2,3);

        var result = FilterNonZeroElements.notZeroAndOneSkipWithSortedNumbers(GIVEN_INPUT);

        assertEquals(EXPECTED_INPUT, result);
    }

    @Test
    public void 숫자0_초과하며_10미만인_값이면서_짝수인_리스트(){
        var GIVEN_INPUT = Arrays.asList(1, 0, 3, 2, 122, 10, 4);
        var EXPECTED_INPUT = Arrays.asList(2, 4);

        var result = FilterNonZeroElements.NotBigNotSmallEvenNumbers(GIVEN_INPUT);

        assertEquals(EXPECTED_INPUT, result);
    }

    @Test
    public void 아무_Appollo찾기(){
        var GIVEN_INPUT = List.of("Appollo1","Appollo2","2","hi");

        var result = FindInStream.findAnyAppollo(GIVEN_INPUT);

        assertTrue(result.contains("Appollo"));
    }

    @Test
    public void 첫_번째_Appollo_찾기(){
        var GIVEN_INPUT = List.of("221", "Appollo1","Appollo2","2","hi");

        var result = FindInStream.findFirstAppollo(GIVEN_INPUT);

        assertEquals("Appollo1", result);
    }

    @Test
    public void 유일한_숫자만을_모아서_하나의_String으로_반환(){
        var GIVEN_INPUT = List.of("3", "4", "1", "1", "2", "2");
        var EXPECTED_INPUT = "1,2,3,4";

        var result = JoiningResults.joinDistinctNumberString(GIVEN_INPUT);

        assertEquals(EXPECTED_INPUT, result);
    }

    @Test
    public void 타입에_따른_그룹화_리스트() {
        List<Melon> givenInput = Arrays.asList(
                new GroupingStream.Melon("Crenshaw", 1200, GroupingStream.Sugar.HIGH),
                new Melon("Gac", 3000, Sugar.LOW),
                new Melon("Hemi", 2600, Sugar.HIGH),
                new Melon("Hemi", 1600),
                new Melon("Gac", 1200, Sugar.LOW)
        );

        Map<String, List<Melon>> result = GroupingStream.byTypeList(givenInput);

        assertEquals(2, result.get("Gac").size());
        assertEquals(2, result.get("Hemi").size());
        assertEquals(1, result.get("Crenshaw").size());
    }

    @Test
    public void 무게에_따른_그룹화_리스트() {
        List<Melon> givenInput = Arrays.asList(
                new Melon("Gac", 3000, Sugar.LOW),
                new Melon("Crenshaw", 1200, Sugar.HIGH),
                new Melon("Apollo", 2600, Sugar.MEDIUM),
                new Melon("Horned", 1200, Sugar.HIGH)
        );

        Map<Integer, List<Melon>> result = GroupingStream.byWeightInList(givenInput);

        assertEquals(2, result.get(1200).size());
        assertEquals(1, result.get(3000).size());
        assertEquals(1, result.get(2600).size());
    }

    @Test
    public void 타입에_따른_그룹화_셋() {
        List<Melon> givenInput = Arrays.asList(
                new Melon("Hemi", 2600, Sugar.HIGH),
                new Melon("Hemi", 2600, Sugar.HIGH), // Duplicate
                new Melon("Gac", 1200, Sugar.LOW),
                new Melon("Cantaloupe", 3600, Sugar.MEDIUM)
        );

        Map<String, Set<Melon>> result = GroupingStream.byTypeInSet(givenInput);

        assertEquals(1, result.get("Hemi").size()); // Set removes duplicates
        assertEquals(1, result.get("Gac").size());
        assertEquals(1, result.get("Cantaloupe").size());
    }

    @Test
    public void 설탕과_무게에_따른_그룹화_맵() {
        List<Melon> givenInput = Arrays.asList(
                new Melon("Apollo", 2600, Sugar.MEDIUM),
                new Melon("Gac", 3000, Sugar.LOW),
                new Melon("Hemi", 1600, Sugar.HIGH),
                new Melon("Cantaloupe", 2600, Sugar.MEDIUM),
                new Melon("Gac", 1200, Sugar.LOW)
        );

        Map<Sugar, TreeMap<Integer, Set<String>>> result = GroupingStream.bySugarAndWeight(givenInput);

        assertTrue(result.containsKey(Sugar.LOW));
        assertTrue(result.containsKey(Sugar.MEDIUM));
        assertTrue(result.containsKey(Sugar.HIGH));

        assertEquals(Set.of("Gac"), result.get(Sugar.LOW).get(1200));
        assertEquals(Set.of("Apollo", "Cantaloupe"), result.get(Sugar.MEDIUM).get(2600));
    }
    @Test
    public void 무게별로_파티셔닝하여_리스트_반환() {
        List<Melon> GIVEN_INPUT = Arrays.asList(
                new Melon("Crenshaw", 1200),
                new Melon("Gac", 3000),
                new Melon("Apollo", 2600),
                new Melon("Horned", 1700)
        );

        Map<Boolean, List<Melon>> result = Partitioning.partitionByWeight(GIVEN_INPUT, 2000);

        assertEquals(2, result.get(true).size());
        assertEquals(2, result.get(false).size());
    }

    @Test
    public void 무게별로_파티셔닝하여_셋_반환() {
        List<Melon> GIVEN_INPUT = Arrays.asList(
                new Melon("Crenshaw", 1200),
                new Melon("Gac", 3000),
                new Melon("Apollo", 2600),
                new Melon("Horned", 1700),
                new Melon("Apollo", 2600) // Duplicate
        );

        Map<Boolean, Set<Melon>> result = Partitioning.partitionByWeightToSet(GIVEN_INPUT, 2000);

        assertEquals(2, result.get(true).size());
        assertEquals(2, result.get(false).size());
    }

    @Test
    public void 무게별로_파티셔닝하여_개수별로_맵_반환() {
        List<Melon> GIVEN_INPUT = Arrays.asList(
                new Melon("Crenshaw", 1200),
                new Melon("Gac", 3000),
                new Melon("Apollo", 2600),
                new Melon("Apollo", 2600),
                new Melon("Horned", 1700)
        );

        Map<Boolean, Long> result = Partitioning.partitionByWeightAndCount(GIVEN_INPUT, 2000);

        assertEquals(3L, result.get(true));
        assertEquals(2L, result.get(false));
    }

    @Test
    public void 무게별로_파티셔닝하고_최대무게_반환() {
        List<Melon> GIVEN_INPUT = Arrays.asList(
                new Melon("Crenshaw", 1200),
                new Melon("Gac", 3000),
                new Melon("Apollo", 2600),
                new Melon("Horned", 1700)
        );

        Map<Boolean, Melon> result = Partitioning.partitionByWeightAndMax(GIVEN_INPUT, 2000);

        assertEquals(new Melon("Gac", 3000), result.get(true));
        assertEquals(new Melon("Horned", 1700), result.get(false));
    }
}
