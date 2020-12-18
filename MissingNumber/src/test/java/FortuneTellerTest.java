import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
public class FortuneTellerTest {

    private final FortuneTeller ft = new FortuneTeller();

    @Test
    void testFT() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ft.populateDB(null));
        assertEquals(ex.getMessage(), "NO FILE SPECIFIED: file name is invalid");
    }

    @Test
    void readLocal() {
        ft.populateDB(new File("/Users/ss670121/sourcebox/learn/golang/exercism/java/MissingNumber/src/test/resources/fortune.txt"));
        assertEquals(ft.myFortune(0), "My life is simple\n");
        assertEquals(ft.myFortune(1), "Life is easier when you have the right set of people around you,\n" +
                "as friends and family to help you enjoy the moment.\n" +
                "   - Saurabh\n");
        assertEquals(ft.myFortune(2), "S\n");
        assertEquals(ft.myFortune(3), "Life is never that simple\n");
    }
    
}
