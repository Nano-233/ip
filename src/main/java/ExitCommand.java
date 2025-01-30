public class ExitCommand extends Command {
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    System.out.println("Bye~");
  }

  @Override
  public boolean isExit() {
    return true;
  }
}
