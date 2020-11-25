import java.util.*;
import java.util.stream.IntStream;

class Yacht {

    private int count = 0;

    Yacht(int[] dice, YachtCategory yachtCategory) {

        switch (yachtCategory) {
            case YACHT:
                if (Arrays.stream(dice).distinct().count() == 1) {
                    count = 50;
                }
                break;

            case ONES:
            case TWOS:
            case THREES:
            case FOURS:
            case FIVES:
            case SIXES:
                count = Arrays.stream(dice).filter(x -> x == yachtCategory.ordinal()).sum();
                break;

            case FOUR_OF_A_KIND:
                // At least four dice showing the same face
                // Find the distinct elements and out of the distinct
                // the number should appear a minimum 4 times.
                count = Arrays.stream(dice).distinct().map(y -> {
                    if (Arrays.stream(dice).filter(x -> x == y).count() < 4){
                        return 0;
                    }
                    return 4 * y;
                }).sum();
                break;

            case LITTLE_STRAIGHT:
                if ((Arrays.stream(dice).distinct().count() == 5) && (Arrays.stream(dice).distinct().max().getAsInt() == 5)) {
                    count = Math.floorDiv(Arrays.stream(dice).distinct().reduce(0, (a, b) -> {
                        return a + b;
                    }), 15) * 30;
                }
                break;
            case CHOICE:
                count = Arrays.stream(dice).reduce(0, (a1, a2) -> a1 + a2);
                break;
            case BIG_STRAIGHT:
                if ((Arrays.stream(dice).distinct().count() == 5) && (Arrays.stream(dice).distinct().min().getAsInt() == 2)) {
                    count = (int) Math.floorDiv(Arrays.stream(dice).distinct().reduce(0, (a, b) -> {
                        return a + b;
                    }), 20) * 30;
                }
                break;
            case FULL_HOUSE:
                if (Arrays.stream(dice).distinct().count() == 2) {
                    count = Arrays.stream(dice).distinct().flatMap(distinct -> {
                        long value = Arrays.stream(dice).filter(x -> x == distinct).count();
                        if (value > 1 && value <= 3) {
                            return IntStream.of(Arrays.stream(dice).filter(x -> x == distinct).sum());
                        } else {
                            return IntStream.of(0);
                        }
                    }).sum();
                }
                break;
            default:
                count = 0;
                break;

        }

    }

    int score() {
        return this.count;
    }

}
