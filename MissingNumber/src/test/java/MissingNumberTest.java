import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;


import static org.junit.jupiter.api.Assertions.*;
public class MissingNumberTest {
    @Test
    public void testException() {
        MissingNumber msn;
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new MissingNumber(null));
        assertEquals(ex.getMessage(), "please define a valid list of integers");
    }

    @Test
    public void uniqueNumericMissing3() {
        Collection eList = Arrays.asList(1, 2, 4, 5);
        MissingNumber msn = new MissingNumber(eList);
        // That number is not there in the list
        assertFalse(eList.contains(msn.findTheMissingNumber()));
    }

    @Test
    public void uniqueNumericMissing9() {
        Collection eList = Arrays.asList(1, 2, 4, 5, 3, 8, 7, 6, 10);
        MissingNumber msn = new MissingNumber(eList);
        // That number is not there in the list
        assertFalse(eList.contains(msn.findTheMissingNumber()));
    }


    @Test
    public void dupNumericMissing9() {
        Collection eList = Arrays.asList(1, 2, 4, 5, 3, 8, 7, 6, 10, 10, 2, 4, 3);
        MissingNumber msn = new MissingNumber(eList);
        // That number is not there in the list
        assertFalse(eList.contains(msn.findTheMissingNumber()));
    }


    @Test
    public void dupNumericMissing4() {
        Collection eList = Arrays.asList(1, 2, 9, 5, 3, 8, 7, 6, 10, 10, 2, 1, 3);
        MissingNumber msn = new MissingNumber(eList);
        // That number is not there in the list
        assertFalse(eList.contains(msn.findTheMissingNumber()));
    }
}
