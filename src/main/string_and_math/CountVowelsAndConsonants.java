package src.main.string_and_math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//자음과 모음 개수 세기
public class CountVowelsAndConsonants {
    private static final Set<Character> allVowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public Pair<Long, Long> v1(String str) {

        str = str.toLowerCase();

        long vowels = 0;
        long consonants = 0;
        for (char c : str.toCharArray()) {
            if (allVowels.contains(c)) {
                vowels++;
            } else if ((c >= 'a' && c <= 'z')) {
                consonants++;
            }
        }

        return Pair.of(vowels, consonants);
    }

    public  Pair<Long, Long> v2(String str) {
        str = str.toLowerCase();

        long vowels = str.chars()
                .filter(c -> allVowels.contains((char) c))
                .count();

        long consonants = str.chars()
                .filter(c -> !allVowels.contains((char) c))
                .filter(ch -> (ch >= 'a' && ch <= 'z'))
                .count();

        return Pair.of(vowels, consonants);
    }

    public  Pair<Long, Long> v3(String str) {

        Map<Boolean, Long> result = str.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .filter(ch -> (ch >= 'a' && ch <= 'z'))
                .collect(Collectors.partitioningBy(allVowels::contains, Collectors.counting()));

        return Pair.of(result.get(true), result.get(false));
    }

    public static final class Pair<V, C> {

        final V vowels;
        final C consonants;

        public Pair(V vowels, C consonants) {
            this.vowels = vowels;
            this.consonants = consonants;
        }

        public static <V, C> Pair<V, C> of(V vowels, C consonants) {
            return new Pair<>(vowels, consonants);
        }

        @Override
        public int hashCode() {
            return vowels.hashCode() ^ consonants.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair obj)) {
                return false;
            }

            return this.vowels.equals(obj.vowels)
                    && this.consonants.equals(obj.consonants);
        }
    }
}
