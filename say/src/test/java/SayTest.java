import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.assertEquals;

public class SayTest {

    private final Say say = new Say();

    @Test
    public void zero() {
        assertEquals("zero", say.say(0));
    }

    @Test
    public void one() {
        assertEquals("one", say.say(1));
    }

    @Test
    public void fourteen() {
        assertEquals("fourteen", say.say(14));
    }

    @Test
    public void fiveteen() {
        assertEquals("fifteen", say.say(15));
    }

    @Test
    public void sixteen() {
        assertEquals("sixteen", say.say(16));
    }

    @Test
    public void seventeen() {
        assertEquals("seventeen", say.say(17));
        assertEquals("eighteen", say.say(18));
        assertEquals("nineteen", say.say(19));
    }

    @Test
    public void twenty() {
        assertEquals("twenty", say.say(20));
    }

    @Test
    public void twentyTwo() {
        assertEquals("twenty-two", say.say(22));
    }
    public void thirtyTwo() {
        assertEquals("thirty-two", say.say(32));
        assertEquals("thirty-four", say.say(34));
        assertEquals("thirty-nine", say.say(39));
        assertEquals("forty-nine", say.say(49));
        assertEquals("fifty-nine", say.say(59));
        assertEquals("sixty-nine", say.say(69));
        assertEquals("eighty-nine", say.say(89));
        assertEquals("seventy-nine", say.say(79));
        assertEquals("ninety-nine", say.say(99));
    }

    @Test
    public void oneHundred() {
        assertEquals("one hundred", say.say(100));
    }

    @Test
    public void oneHundredTwentyThree() {
        assertEquals("one hundred twenty-three", say.say(123));
    }

    @Test
    public void oneHundredSeries() {
        assertEquals("one hundred thirty-three", say.say(133));
        assertEquals("one hundred forty-three", say.say(143));
        assertEquals("one hundred forty-six", say.say(146));
        assertEquals("one hundred fifty-six", say.say(156));
        assertEquals("one hundred sixty-six", say.say(166));
        assertEquals("one hundred ninety-nine", say.say(199));
        assertEquals("two hundred ninety-nine", say.say(299));
        assertEquals("nine hundred ninety-nine", say.say(999));
    }

    @Test
    public void oneThousand() {
        assertEquals("one thousand", say.say(1_000));
    }

    @Test
    public void oneThousandTwoHundredThirtyFour() {
        assertEquals("one thousand two hundred thirty-four", say.say(1_234));
    }

    @Test
    public void oneMillion() {
        assertEquals("one million", say.say(1_000_000));
    }

    @Test
    public void oneMillionTwoThousandThreeHundredFortyFive() {
        assertEquals("one million two thousand three hundred forty-five", say.say(1_002_345));
    }

    @Test
    public void oneBillion() {
        assertEquals("one billion", say.say(1_000_000_000));
        assertEquals("two billion", say.say(2_000_000_000));
        assertEquals("two billion twenty-three", say.say(2_000_000_023));
        assertEquals("two billion one hundred twenty-three", say.say(2_000_000_123));
        assertEquals("two billion three hundred twenty-one thousand one hundred twenty-three", say.say(2_000_321_123));
        assertEquals("two billion nine hundred twenty-one million three hundred twenty-one thousand one hundred twenty-three", say.say(2921321123l));
    }
    @Test
    public void threeBillion() {
        assertEquals("three billion nine hundred twenty-one million three hundred twenty-one thousand one hundred twenty-three", say.say(3921321123l));
    }

    @Test
    public void nineHundredEightySevenBillionSixHundredFiftyFourThreeHundredTwentyOneThousandOneHundredTwentyThree() {
        assertEquals("nine hundred eighty-seven billion six hundred fifty-four million" +
                " three hundred twenty-one thousand one hundred twenty-three", say.say(987_654_321_123L));
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalNegativeNumber() {
        say.say(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalTooBigNumber() {
        say.say(1_000_000_000_000L);
    }
}
