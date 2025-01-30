public class DeleteCommand extends Command {
  private final int index;

  public DeleteCommand(String input) throws LunaException {
    try {
      this.index = Integer.parseInt(input.split(" ")[1]) - 1;
    } catch (Exception e) {
      throw new LunaException(LunaException.ErrorType.INVALID_FORMAT, "Correct format for delete is `delete <task number>`");
    }
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws LunaException {
    Task removedTask = tasks.getTasks().remove(index);
    storage.saveTasks(tasks.getTasks());
    System.out.println("Oki~ I've removed this task!:");
    System.out.println(removedTask);
    System.out.println("Now you have " + tasks.getTasks().size() + " tasks left in the list~");
  }
}
