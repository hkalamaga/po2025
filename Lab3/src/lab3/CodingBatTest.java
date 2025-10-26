package lab3;
import org.junit.Test;
import static org.junit.Assert.*;

public class CodingBatTest {

    @Test
    public void testSleepIn() {
        assertTrue(CodingBat.sleepIn(false, false));
        assertFalse(CodingBat.sleepIn(true, false));
        assertTrue(CodingBat.sleepIn(true, true));
    }

    @Test
    public void testMonkeyTrouble() {
        assertTrue(CodingBat.monkeyTrouble(true, true));
        assertTrue(CodingBat.monkeyTrouble(false, false));
        assertFalse(CodingBat.monkeyTrouble(true, false));
        assertFalse(CodingBat.monkeyTrouble(false, true));
    }

    @Test
    public void testHelloName() {
        assertEquals("Hello Alice!", CodingBat.helloName("Alice"));
        assertEquals("Hello Bob!", CodingBat.helloName("Bob"));
        assertEquals("Hello Eve!", CodingBat.helloName("Eve"));
    }

    @Test
    public void testCountEvens() {
        assertEquals(3, CodingBat.countEvens(new int[]{2, 1, 2, 3, 4}));
        assertEquals(3, CodingBat.countEvens(new int[]{2, 2, 0}));
        assertEquals(0, CodingBat.countEvens(new int[]{1, 3, 5}));
    }
}
