import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Some constants
 */
interface NUMBERS {
    // final is implicit for constants in interface
    long MAX = 1000000000000l;
    long BILLION = 1000000000;
    long MILLION = 1000000L;
    long THOUSAND = 1000L;
    long HUNDRED = 100l;
}

/**
 * Say - Converts a number into Textual representation.
 */
public class Say {
    /**
     * Spell out the Number
     * @param value
     * @return
     */
    public String say(long value) {
        // Less than zero not valid
        if (value < 0) {
            throw new IllegalArgumentException("number should be greater than zero");
        }

        // Max limit defined
        if (value >= NUMBERS.MAX) {
            throw new IllegalArgumentException("number too big");
        }

        // Translator method
        return oneRounded(value);
    }

    private static final Map<Long, String> nMap = new LinkedHashMap<>() {
        {
            put(0L, "zero");
            put(1L, "one");
            put(2L, "two");
            put(3L, "three");
            put(4L, "four");
            put(5L, "five");
            put(6L, "six");
            put(7L, "seven");
            put(8L, "eight");
            put(9L, "nine");
            put(10L, "ten");
            put(11L, "eleven");
            put(12L, "twelve");
            put(13L, "thirteen");
            put(14L, "fourteen");
            put(15L, "fifteen");
            put(16L, "sixteen");
            put(17L, "seventeen");
            put(18L, "eighteen");
            put(19L, "nineteen");
            put(20L, "twenty");
            put(30L, "thirty");
            put(40L, "forty");
            put(50L, "fifty");
            put(60L, "sixty");
            put(70L, "seventy");
            put(80L, "eighty");
            put(90L, "ninety");
            put(NUMBERS.HUNDRED, "hundred");
            put(NUMBERS.THOUSAND, "thousand");
            put(NUMBERS.MILLION, "million");
            put(NUMBERS.BILLION, "billion");
        }
    };

    private long getFaceValue(long value, long div) {
        return (((value / div) == 0) ? 1 : (value / div));
    }

    // default is 10
    private long getPlaceValue(long value) {
        return getPlaceValue(value, 10);
    }

    private long getPlaceValue(long value, long place) {
        return (value * place);
    }
    /**
     * Finds the unit value
     *
     * @param value
     * @param div
     * @return
     */
    private String getUnits(long value, long div) {
        return nMap.get(getFaceValue(value, div));
    }

    /**
     * Utility method that shall translate all the values recursively
     * @param value
     * @return
     */
    private String oneRounded(long value) {
        // Get all the keys - Template values
        Set<Long> keys = nMap.keySet();

        // Not found and less than 100 [20 - 100]
        if (!keys.contains(Long.valueOf(value)) && value < NUMBERS.HUNDRED) {
            return String.format("%s-%s", nMap.get(getPlaceValue(value/10)), nMap.get(value%10));
        }


        if (value >= NUMBERS.HUNDRED && value < NUMBERS.THOUSAND) {

            if ((value % NUMBERS.HUNDRED) == 0) {
                // Hundred
                return String.format("%s %s", nMap.get(value/NUMBERS.HUNDRED), nMap.get(NUMBERS.HUNDRED));
            }

            return String.format("%s %s %s", oneRounded(value/NUMBERS.HUNDRED), nMap.get(NUMBERS.HUNDRED), oneRounded(value%NUMBERS.HUNDRED));
        }

        if (value >= NUMBERS.THOUSAND && value < NUMBERS.MILLION) {

            if ((value % NUMBERS.THOUSAND) == 0) {
                // Thousand
                return String.format("%s %s", getUnits(value, NUMBERS.THOUSAND), nMap.get(NUMBERS.THOUSAND));
            } else {
              return String.format("%s %s %s", oneRounded(value/NUMBERS.THOUSAND), nMap.get(NUMBERS.THOUSAND), oneRounded(value%NUMBERS.THOUSAND));
            }
        }


        if (value >= NUMBERS.MILLION && value < NUMBERS.BILLION) {
            if ((value % NUMBERS.MILLION) == 0) {
                // Million
                return String.format("%s %s", getUnits(value, NUMBERS.MILLION), nMap.get(NUMBERS.MILLION));
            }else {
                return String.format("%s %s %s", oneRounded((value/NUMBERS.MILLION)), nMap.get(NUMBERS.MILLION), oneRounded(value%NUMBERS.MILLION));
            }
        }

        if (value >= NUMBERS.BILLION) {
            if ((value % NUMBERS.BILLION) == 0) {
                // Billion
                return String.format("%s %s", getUnits(value, NUMBERS.BILLION), nMap.get(NUMBERS.BILLION));
            } else {
                return String.format("%s %s %s", oneRounded(value/NUMBERS.BILLION), nMap.get(NUMBERS.BILLION), oneRounded(value%NUMBERS.BILLION));
            }
        }

        return nMap.get(value).toLowerCase();
    }
}
