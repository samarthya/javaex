import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Grains on a board.
 * 1 + 2 + 6 + 18 + .......
 */
class Grains {

    private Map<Integer, BigInteger> lHM = new LinkedHashMap<>() {
        {
            put(Integer.valueOf(1), BigInteger.ONE);
        }
    };

    BigInteger simpleMethodBeforeStream(final int square) {
        if (square < 1 || square > 64)
        {
            throw new IllegalArgumentException("square must be between 1 and 64");
        }
        if (lHM.containsKey(square)) {
            return lHM.get(square);
        }

        for(int i=1; i< square; i++) {

            lHM.put(i+1, lHM.get(i).multiply(BigInteger.TWO));
        }
        return lHM.get(square);
    }

    BigInteger grainsOnSquare(final int square) {
        return simpleMethodBeforeStream(square);
    }

    BigInteger grainsOnBoard() {

        return BigInteger.ONE;
    }

}
