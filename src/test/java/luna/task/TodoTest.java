package luna.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the {@link Todo} class to ensure correct functionality.
 */
class TodoTest {

    /**
     * Ensures that a {@link Todo} task is created correctly with the provided description.
     * Also verifies that tags can be added and reflected in the string representation.
     */
    @Test
    void todoCreation_validDescription_success() {
        Todo todo = new Todo("Finish homework");
        assertEquals("[T][ ] Finish homework", todo.toString());

        // Test with tags
        todo.addTag("school");
        assertEquals("[T][ ] Finish homework #school", todo.toString());
    }

    /**
     * Ensures that the file format representation of a {@link Todo} task is correct.
     * Verifies that tags are included properly in the output format.
     */
    @Test
    void toFileFormat_validTodo_correctFormat() {
        Todo todo = new Todo("Go jogging");
        assertEquals("T | 0 |   | Go jogging", todo.toFileFormat()); // Empty tags should be an empty field

        // Test with tags
        todo.addTag("exercise");
        todo.addTag("morning");
        assertEquals("T | 0 | exercise,morning | Go jogging", todo.toFileFormat());
    }

    /**
     * Ensures that the description of a {@link Todo} task is stored correctly.
     */
    @Test
    void todoCreation_validDescription_storesCorrectDescription() {
        Todo todo = new Todo("Buy groceries");
        assertEquals("Buy groceries", todo.description);
    }
}
