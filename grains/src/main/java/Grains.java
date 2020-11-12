import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

import java.util.stream.IntStream;

/**
 * Grains on a board.
 * 1 + 2 + 6 + 18 + .......
 */
class Grains {

    BigInteger grainsOnSquare(final int square) {
        if (square < 1 || square > 64) {
            throw new IllegalArgumentException("square must be between 1 and 64");
        }
       return BigInteger.TWO.pow(square - 1);
    }

    BigInteger grainsOnBoard() {
        return IntStream.range(1, 65).mapToObj( i -> grainsOnSquare(i)).reduce(BigInteger::add).get();
    }

}
