package luna.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    /**
     * Description of the task.
     */
    protected String description;

    /**
     * Status of the task (done or not).
     */
    protected boolean isDone;

    /**
     * Constructs a task with a given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for the task.
     *
     * @return "X" if the task is done, " " if not done.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task (marks it as not completed).
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String format of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task to a string format for file storage.
     *
     * @return Formatted string representation for saving to file.
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Constructs a task from a given file line.
     *
     * @param line The line from the file containing task details.
     * @return The corresponding Task object, or null if the format is incorrect.
     */
    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            System.out.println("Skipping corrupted task (invalid format): " + line);
            return null;
        }

        Task task;
        try {
            switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                if (parts.length < 4) {
                    System.out.println("Skipping corrupted deadline task (missing date): " + line);
                    return null;
                }
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                if (parts.length < 5) {
                    System.out.println("Skipping corrupted event task (missing dates): " + line);
                    return null;
                }
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                System.out.println("Skipping unknown task type: " + line);
                return null;
            }

            if ("1".equals(parts[1])) {
                task.mark();
            }

            System.out.println("Loaded task: " + task); // Debugging log
            return task;
        } catch (Exception e) {
            System.out.println("Skipping corrupted task: " + line);
            return null;
        }
    }
}
