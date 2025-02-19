package luna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import luna.command.AddCommand;
import luna.command.MarkCommand;

/**
 * Tests the {@link Parser} class to ensure correct command parsing behavior.
 */
class ParserTest {

    /**
     * Ensures that a valid "todo" command is correctly parsed into an {@link AddCommand}.
     *
     * @throws LunaException if the command parsing encounters an error.
     */
    @Test
    void parse_validTodoCommand_returnsAddCommand() throws LunaException {
        Parser parser = new Parser();
        assertTrue(parser.parse("todo Read book") instanceof AddCommand);
    }

    /**
     * Ensures that a valid "mark" command is correctly parsed into a {@link MarkCommand}.
     *
     * @throws LunaException if the command parsing encounters an error.
     */
    @Test
    void parse_validMarkCommand_returnsMarkCommand() throws LunaException {
        Parser parser = new Parser();
        assertTrue(parser.parse("mark 1") instanceof MarkCommand);
    }

    /**
     * Ensures that an invalid command throws a {@link LunaException} with the expected message.
     */
    @Test
    void parse_invalidCommand_throwsException() {
        try {
            Parser parser = new Parser();
            parser.parse("invalidCommand");
            fail();
        } catch (LunaException e) {
            assertEquals("Sorry :< I don't quite understand", e.getMessage());
        }
    }
}
