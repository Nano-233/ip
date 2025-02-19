package luna.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import luna.LunaException;

/**
 * Tests the {@link Event} class to ensure correct functionality.
 */
class EventTest {

    /**
     * Ensures that the string representation of an event is correctly formatted.
     *
     * @throws LunaException if the date format is invalid.
     */
    @Test
    void toString_correctFormat_success() throws LunaException {
        Event event = new Event("Meeting", "10/11/2025", "12/11/2025");
        assertEquals("[E][ ] Meeting (from: 10 of November 2025 to: 12 of November 2025)", event.toString());

        // Test with tags
        event.addTag("work");
        assertEquals("[E][ ] Meeting (from: 10 of November 2025 to: 12 of November 2025) #work", event.toString());
    }

    /**
     * Ensures that the event is correctly converted to a file format string.
     *
     * @throws LunaException if the date format is invalid.
     */
    @Test
    void toFileFormat_validEvent_correctFormat() throws LunaException {
        Event event = new Event("Conference", "5/08/2025", "7/08/2025");
        assertEquals("E | 0 |   | Conference | 5/8/2025 | 7/8/2025", event.toFileFormat());

        // Test with tags
        event.addTag("business");
        event.addTag("important");
        assertEquals("E | 0 | important,business | Conference | 5/8/2025 | 7/8/2025", event.toFileFormat());
    }
}
