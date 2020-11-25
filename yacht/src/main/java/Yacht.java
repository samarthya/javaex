import java.util.*;
import java.util.stream.IntStream;

class Yacht {

    private int count = 0;

    Yacht(int[] dice, YachtCategory yachtCategory) {
        if (Arrays.stream(dice).distinct().count() == 1 && yachtCategory == YachtCategory.YACHT) {
            count = 50;
        }

        if (yachtCategory == YachtCategory.ONES) {
            count = 1 * (int) Arrays.stream(dice).filter(x -> x == 1).count();
        }

        if (yachtCategory == YachtCategory.TWOS) {
            count = 2 * (int) Arrays.stream(dice).filter(x -> x == 2).count();
        }
        if (yachtCategory == YachtCategory.FOURS) {
            count = 4 * (int) Arrays.stream(dice).filter(x -> x == 4).count();
        }

        if (yachtCategory == YachtCategory.THREES) {
            count = 3 * (int) Arrays.stream(dice).filter(x -> x == 3).count();
        }

        if (yachtCategory == YachtCategory.FIVES) {
            count = 5 * (int) Arrays.stream(dice).filter(x -> x == 5).count();
        }

        if (yachtCategory == YachtCategory.SIXES) {
            count = 6 * (int) Arrays.stream(dice).filter(x -> x == 6).count();
        }

        if (yachtCategory == YachtCategory.FOUR_OF_A_KIND) {
            count = Arrays.stream(dice).distinct().flatMap(distinct -> {
                long value = Arrays.stream(dice).filter(x -> x == distinct).count();
                if (value >= 4) {
                    return IntStream.of(4 * distinct);
                } else {
                    return IntStream.of(0);
                }
            }).sum();

        }

        // Useless Reductions.
        if ((yachtCategory == YachtCategory.LITTLE_STRAIGHT) && (Arrays.stream(dice).distinct().count() == 5) && (Arrays.stream(dice).distinct().max().getAsInt() == 5)) {
            count = (int) Math.floorDiv(Arrays.stream(dice).distinct().reduce(0, (a, b) -> {
                return a + b;
            }), 15) * 30;
        }

        if ((yachtCategory == YachtCategory.CHOICE)) {
           count = Arrays.stream(dice).reduce(0, (a1,a2) -> a1+a2);
        }

        if ((yachtCategory == YachtCategory.YACHT) && (Arrays.stream(dice).distinct().count() == 1)) {
            count = 50;
        }

        if ((yachtCategory == YachtCategory.BIG_STRAIGHT) && (Arrays.stream(dice).distinct().count() == 5) && (Arrays.stream(dice).distinct().min().getAsInt() == 2)) {
            count = (int) Math.floorDiv(Arrays.stream(dice).distinct().reduce(0, (a, b) -> {
                return a + b;
            }), 20) * 30;
        }


        // For a Full House
        // Total of the dice
        // Three of one number and two of another	3 3 3 5 5 scores 19
        // [[3,3,3],[5,5]]
        if ((yachtCategory == YachtCategory.FULL_HOUSE) && (Arrays.stream(dice).distinct().count() == 2)) {

            // Step 1
            // [1,4,4,4,4]
            // Distinct [1,4]
            // [1],[4,4,4,4]
            count = Arrays.stream(dice).distinct().flatMap(distinct -> {
                long value = Arrays.stream(dice).filter(x -> x == distinct).count();
                if (value > 1 && value <= 3) {
                    return IntStream.of(Arrays.stream(dice).filter(x -> x == distinct).sum());
                } else {
                    return IntStream.of(0);
                }
            }).sum();

            // count = IntStream.of(dice).reduce((i1, i2) -> i1 + i2).getAsInt();
        }

    }

    int score() {
        return this.count;
    }

}
