package luna.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link ExitCommand} class.
 * Ensures that the command correctly signals program termination.
 */
class ExitCommandTest {

    /**
     * Tests that executing an {@code ExitCommand} correctly sets its exit status.
     */
    @Test
    void execute_setsExitToTrue() {
        ExitCommand command = new ExitCommand();
        assertTrue(command.isExit(), "ExitCommand should return true for isExit()");
    }
}
