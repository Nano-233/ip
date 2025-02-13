package luna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import luna.command.AddCommand;
import luna.command.MarkCommand;

class ParserTest {

    @Test
    void parse_validTodoCommand_returnsAddCommand() throws LunaException {
        Parser parser = new Parser();
        assertTrue(parser.parse("todo Read book") instanceof AddCommand);
    }

    @Test
    void parse_validMarkCommand_returnsMarkCommand() throws LunaException {
        Parser parser = new Parser();
        assertTrue(parser.parse("mark 1") instanceof MarkCommand);
    }

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
