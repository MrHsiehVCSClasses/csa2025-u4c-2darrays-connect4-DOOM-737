package u8pp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReservationTest {
    
    @Test
    public void getPassengerName_ShouldReturnThePassengerName() {
        Reservation testReservation = new Reservation("testName", true);
        assertEquals("testName", testReservation.getPassengerName());
    }

    @Test
    public void getFrequentFlyers_ShouldReturnTrueIfIsAFrequentFlyer()
    {
        Reservation testReservation = new Reservation("testName", true);
        assertTrue(testReservation.isFrequentFlyer());
    }

    @Test
    public void getFrequentFlyers_ShouldReturnFalseIfNotAFrequentFlyer()
    {
        Reservation testReservation = new Reservation("testName", false);
        assertFalse(testReservation.isFrequentFlyer());
    }
}
