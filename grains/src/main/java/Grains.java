import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

import java.util.stream.Stream;

/**
 * Grains on a board.
 * 1 + 2 + 6 + 18 + .......
 */
class Grains {

    private Map<Integer, BigInteger> lHM = new LinkedHashMap<>();

    public Grains() {
        this.Initialize();
    }

    private void Initialize() {
        for(int i=0; i<64; i++) {
            if (i == 0) {
                lHM.put(Integer.valueOf(1), BigInteger.ONE);
                continue;
            }
            lHM.put(i+1, lHM.get(i).multiply(BigInteger.TWO));
        }
    }

    BigInteger grainsOnSquare(final int square) {
        if (square < 1 || square > 64)
        {
            throw new IllegalArgumentException("square must be between 1 and 64");
        }
        if (lHM.containsKey(square)) {
            return lHM.get(square);
        }
        return lHM.get(square);
    }

    BigInteger grainsOnBoard() {
        return lHM.values().stream().reduce(BigInteger::add).get();
    }

}
