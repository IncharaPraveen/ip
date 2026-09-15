package computa;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    TaskList dummyList = new TaskList();
    Ui dummyUi = new Ui();
    Storage dummyStorage = new Storage("dummy.txt");
    @Test
    public void parseDeadline_missingDescription_throwsComputaException() {
        // Simulating the user typing a deadline with no description
        Exception exception = assertThrows(Computa.ComputaException.class, () -> {
            // Adjust this method call to match how your code actually parses commands
            Parser.parse("deadline yearn", dummyList, dummyUi, dummyStorage);
        });

        // Verifying your specific error message is triggered
        assertEquals("no deadline?", exception.getMessage());
    }
}