public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getStatusIcon() {
    return isDone ? "X" : " ";
  }

  public void mark() {
    isDone = true;
  }

  public void unmark() {
    isDone = false;
  }

  public String toString() {
    return "[" + getStatusIcon() + "] " + description;
  }

  public String toFileFormat() {
    return "T | " + (isDone ? "1" : "0") + " | " + description;
  }

  public static Task fromFileFormat(String line) {
    String[] parts = line.split(" \\| ");
    if (parts.length < 3) return null;

    Task task;
    switch (parts[0]) {
      case "T":
        task = new Todo(parts[2]);
        break;
      case "D":
        task = new Deadline(parts[2], parts[3]);
        break;
      case "E":
        task = new Event(parts[2], parts[3], parts[4]);
        break;
      default:
        return null;
    }

    if ("1".equals(parts[1])) {
      task.mark();
    }
    return task;
  }
}
