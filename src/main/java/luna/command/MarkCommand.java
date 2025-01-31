package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;
import luna.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String input) throws LunaException {
        try {
            this.index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT, "Correct format for mark is `mark <task number>`");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LunaException {
        tasks.markTask(index);
        storage.saveTasks(tasks.getTasks());
        System.out.println("Yay!~ I've marked this task as done:");
        System.out.println(tasks.getTasks().get(index));
    }
}
