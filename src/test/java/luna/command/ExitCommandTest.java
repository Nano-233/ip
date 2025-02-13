package luna.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ExitCommandTest {

    @Test
    void execute_setsExitToTrue() {
        ExitCommand command = new ExitCommand();
        assertTrue(command.isExit());
    }
}
