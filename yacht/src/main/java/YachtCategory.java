
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

import static java.util.function.Function.*;

// Every enum is a Java Class
enum YachtCategory {

    YACHT {
        @Override
        int sum(int[] dice) {
//            if (Arrays.stream(dice).distinct().count() == 1) {
//                return 50;
//            }
//            return 0;
            return numberMap(dice).size() == 1 ? 50 : 0;
        }
    },
    ONES{
        @Override
        int sum(int[] dice) {
            //count = Arrays.stream(dice).filter(x -> x == yachtCategory.ordinal()).sum();
            return numberMap(dice).getOrDefault(1, 0);
        }
    },
    TWOS{
        @Override
        int sum(int[] dice) {
            return 2 * numberMap(dice).getOrDefault(2, 0);
        }
    },
    THREES{
        @Override
        int sum(int[] dice) {
            return 3 * numberMap(dice).getOrDefault(3, 0);
        }
    },
    FOURS{
        @Override
        int sum(int[] dice) {
            return 4 * numberMap(dice).getOrDefault(4, 0);
        }
    },
    FIVES{
        @Override
        int sum(int[] dice) {
            return 5 *numberMap(dice).getOrDefault(5, 0);
        }
    },
    SIXES{
        @Override
        int sum(int[] dice) {
            return 6*numberMap(dice).getOrDefault(6, 0);
        }
    },
    FULL_HOUSE{
        @Override
        int sum(int[] dice) {
            System.out.println(numberMap(dice).values().toString());
            return numberMap(dice).values().containsAll(List.of(2, 3)) ? Arrays.stream(dice).sum() : 0;
        }
    },
    FOUR_OF_A_KIND{
        @Override
        int sum(int[] dice) {
            return numberMap(dice).entrySet().stream()
                    .filter(e -> e.getValue() >= 4)
                    .map(e -> e.getKey() * 4)
                    .findFirst()
                    .orElse(0);
        }
    },
    LITTLE_STRAIGHT{
        @Override
        int sum(int[] dice) {
            return YachtCategory.numberMap(dice).entrySet().stream()
                    .filter(e -> e.getKey() <= 5)
                    .count() == 5 ? 30 : 0;
        }
    },
    BIG_STRAIGHT{
        @Override
        int sum(int[] dice) {
            return YachtCategory.numberMap(dice).entrySet().stream()
                    .filter(e -> e.getKey() > 1 && e.getKey() <= 6)
                    .count() == 5 ? 30 : 0;
        }
    },
    CHOICE{
        @Override
        int sum(int[] dice) {
            return Arrays.stream(dice).sum();
        }
    };

    abstract int sum(int[] dice);


    private static Map<Integer, Integer> numberMap(int[] dice) {
        //Map<Integer, Integer> local =
        return Arrays
                .stream(dice)
                .boxed()
                .collect(groupingBy(identity(), summingInt(e -> 1)));
        // System.out.println(local);
        // return local;
    }
}
