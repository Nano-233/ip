package luna.task;

import luna.LunaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void todoCreation_validDescription_success() {
        Todo todo = new Todo("Finish homework");
        assertEquals("[T][ ] Finish homework", todo.toString());
    }

    @Test
    void toFileFormat_validTodo_correctFormat() {
        Todo todo = new Todo("Go jogging");
        assertEquals("T | 0 | Go jogging", todo.toFileFormat());
    }

    @Test
    void todoCreation_validDescription_storesCorrectDescription() {
        Todo todo = new Todo("Buy groceries");
        assertEquals("Buy groceries", todo.description);
    }
}
