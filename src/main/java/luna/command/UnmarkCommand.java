package luna.command;

import luna.LunaException;
import luna.storage.Storage;
import luna.task.TaskList;
import luna.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(String input) throws LunaException {
        try {
            this.index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new LunaException(LunaException.ErrorType.INVALID_FORMAT, "Correct format for unmark is `unmark <task number>`");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LunaException {
        tasks.unmarkTask(index);
        storage.saveTasks(tasks.getTasks());
        System.out.println("OKi~ I've marked this task as not done yet:");
        System.out.println(tasks.getTasks().get(index));
    }
}
