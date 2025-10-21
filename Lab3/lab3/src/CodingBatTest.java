import static org.junit.Assert.*;
import org.junit.Test;

public class CodingBatTest {

    @Test
    public void testSleepIn_WeekdayAndVacation() {
        CodingBat c = new CodingBat();
        assertTrue("Test case failed for weekday=true, vacation=true", c.sleepIn(true, true));
    }

    @Test
    public void testSleepIn_WeekdayAndNotVacation() {
        CodingBat c = new CodingBat();
        assertFalse("Test case failed for weekday=true, vacation=false", c.sleepIn(true, false));
    }

    @Test
    public void testSleepIn_NotWeekdayAndVacation() {
        CodingBat c = new CodingBat();
        assertTrue("Test case failed for weekday=false, vacation=true", c.sleepIn(false, true));
    }

    @Test
    public void testSleepIn_NotWeekdayAndNotVacation() {
        CodingBat c = new CodingBat();
        assertTrue("Test case failed for weekday=false, vacation=false", c.sleepIn(false, false));
    }
}
