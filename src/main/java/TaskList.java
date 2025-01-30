import java.util.ArrayList;

public class TaskList {
  private ArrayList<Task> tasks;

  public TaskList() {
    this.tasks = new ArrayList<>();
  }

  public TaskList(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public void deleteTask(int index) throws LunaException {
    if (index < 0 || index >= tasks.size()) {
      throw new LunaException(LunaException.ErrorType.INVALID_TASK_NUMBER, "");
    }
    tasks.remove(index);
  }

  public void markTask(int index) throws LunaException {
    if (index < 0 || index >= tasks.size()) {
      throw new LunaException(LunaException.ErrorType.INVALID_TASK_NUMBER, "");
    }
    tasks.get(index).mark();
  }

  public void unmarkTask(int index) throws LunaException {
    if (index < 0 || index >= tasks.size()) {
      throw new LunaException(LunaException.ErrorType.INVALID_TASK_NUMBER, "");
    }
    tasks.get(index).unmark();
  }

  public ArrayList<Task> getTasks() {
    return tasks;
  }

  public void printList() {
    if (tasks.isEmpty()) {
      System.out.println("Your list is empty :<. Add something!");
    } else {
      System.out.println("Here are the tasks in your list:");
      for (int i = 0; i < tasks.size(); i++) {
        System.out.println((i + 1) + ". " + tasks.get(i));
      }
    }
  }
}
