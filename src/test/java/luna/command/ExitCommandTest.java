package luna.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExitCommandTest {

    @Test
    void execute_setsExitToTrue() {
        ExitCommand command = new ExitCommand();
        assertTrue(command.isExit());
    }
}
