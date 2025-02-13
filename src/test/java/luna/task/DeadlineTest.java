package luna.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import luna.LunaException;

class DeadlineTest {

    @Test
    void toString_correctFormat_success() throws LunaException {
        Deadline deadline = new Deadline("Submit Report", "30/12/2025");
        assertEquals("[D][ ] Submit Report (by: 30 of December 2025)", deadline.toString());
    }

    @Test
    void invalidDateFormat_throwsException() {
        try {
            new Deadline("Submit Report", "not_a_date");
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Correct format"));
        }
    }
}
