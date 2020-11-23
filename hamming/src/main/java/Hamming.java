import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Hamming {
    private String StrandOne;
    private String StrandTwo;

    public Hamming(String leftStrand, String rightStrand) {
        this.StrandOne = leftStrand;
        this.StrandTwo = rightStrand;

        if ((StrandOne.isEmpty()) && (!StrandTwo.isEmpty())) {
            throw new IllegalArgumentException("left strand must not be empty.");
        }
        if ((StrandTwo.isEmpty()) && (!StrandOne.isEmpty())) {
            throw new IllegalArgumentException("right strand must not be empty.");
        }
        if (StrandOne.length() != StrandTwo.length()) {
            throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
        }
    }

    public int getHammingDistance() {
        return ((int) IntStream.range(0, StrandOne.length()).filter(i -> Character.compare(StrandOne.charAt(i), StrandTwo.charAt(i)) != 0).count());
    }
}
