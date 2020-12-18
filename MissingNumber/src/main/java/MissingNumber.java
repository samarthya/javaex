import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MissingNumber {
    private final Logger logger = Logger.getLogger(MissingNumber.class.getName());
    private final ArrayList<Integer> numberList;

    public MissingNumber(Collection list) {
        if (list == null) {
            throw new IllegalArgumentException("please define a valid list of integers");
        }

        this.numberList = new ArrayList<Integer>(list);
    }

    // No Duplicate method
    private int findInNoDuplicate() {
        Integer n = this.numberList.stream().sorted(Comparator.reverseOrder()).max(Integer::max).get();
        Integer sumN = Integer.valueOf(Math.floorDiv(Math.multiplyExact(n.intValue() , n.intValue()+1), 2));

        logger.log(Level.INFO, " N:          " + n.intValue());
        logger.log(Level.INFO, "(N (N+1))/2: " + sumN);
        logger.log(Level.INFO, " Sum:        " + this.numberList.stream().collect(Collectors.summingInt(Integer::intValue)));

        return  this.numberList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.summingInt(Integer::intValue)) - sumN;
    }

    private int findWithDuplicates() {
        Integer n = this.numberList.stream().sorted(Comparator.reverseOrder()).max(Integer::max).get();
        Integer m = this.numberList.stream().sorted(Comparator.naturalOrder()).max(Integer::min).get();
        BitSet bs = new BitSet(n);

        // Set the index bit for the element just found
        this.numberList.stream().sorted(Comparator.naturalOrder()).forEach( x -> bs.set(x));
        logger.log(Level.INFO, " E: " + bs.nextClearBit(m));
        return bs.nextClearBit(m);
    }


    public int findTheMissingNumber() {
        //return findInNoDuplicate();
        return findWithDuplicates();
    }
}
