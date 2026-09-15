package computa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.format.DateTimeParseException;

public class DeadlineTest {

    @Test
    public void testDeadlineCreation_validMilitaryTime_success() {
        // Testing standard valid input
        Deadline d = new Deadline("return book", "2026-12-01 2359");

        // Adjust the expected string below to match exactly what your toString() outputs
        assertEquals("[D][ ] return book (by: Dec 01 2026 11.59pm)", d.toString());
    }

    @Test
    public void testDeadlineCreation_invalidFormat_exceptionThrown() {
        // Testing the exact crash you fixed earlier to ensure the app catches it
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("return book", "01-12-2026 1159");
        });
    }
}