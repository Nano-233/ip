package luna.task;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import luna.LunaException;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Set<String> tags;

    /**
     * Constructs a task with a given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new HashSet<>();
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
     * Retrieves the task description.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
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
     * Adds a tag to the task.
     *
     * @param tag The tag to add.
     */
    public void addTag(String tag) {
        if (tag != null && !tag.trim().isEmpty()) {
            tags.add(tag.trim());
        }
    }

    /**
     * Returns the tags in a readable format.
     *
     * @return A formatted string of tags.
     */
    public String tagToString() {
        return tags.isEmpty() ? "" : " #" + String.join(" #", tags);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String format of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + tagToString();
    }

    /**
     * Converts the task to a string format for file storage.
     *
     * @return Formatted string representation for saving to file.
     */
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | "
                       + (tags.isEmpty() ? " " : String.join(",", tags)) + " | " + description;
    }


    /**
     * Constructs a task from a given file line.
     *
     * @param line The line from the file containing task details.
     * @return The corresponding Task object, or null if the format is incorrect.
     */
    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ", -1);

        if (parts.length < 4) {
            System.out.println("Skipping corrupted task (invalid format): " + line);
            return null;
        }

        boolean isDone = "1".equals(parts[1]);
        String[] tags = parts[2].trim().isEmpty() ? new String[]{} : parts[2].split(",");

        Task task = createTaskFromParts(parts);
        if (task == null) {
            System.out.println("Skipping unknown task type: " + line);
            return null;
        }

        if (isDone) {
            task.mark();
        }

        for (String tag : tags) {
            task.addTag(tag.trim());
        }

        return task;
    }

    /**
     * Creates a task based on the given file parts.
     *
     * @param parts The parsed file line split into components.
     * @return The corresponding Task object, or null if invalid.
     */
    private static Task createTaskFromParts(String[] parts) {
        try {
            return switch (parts[0]) {
                case "T" -> createTodo(parts);
                case "D" -> createDeadline(parts);
                case "E" -> createEvent(parts);
                default -> null;
            };
        } catch (Exception e) {
            System.out.println("Skipping corrupted task due to exception: " + Arrays.toString(parts));
            return null;
        }
    }

    /**
     * Creates a Todo task from file parts.
     *
     * @param parts The parsed file line split into components.
     * @return A Todo object.
     */
    private static Task createTodo(String[] parts) {
        return new Todo(parts[3]);
    }

    /**
     * Creates a Deadline task from file parts.
     *
     * @param parts The parsed file line split into components.
     * @return A Deadline object, or null if invalid.
     */
    private static Task createDeadline(String[] parts) throws LunaException {
        if (parts.length < 5) {
            System.out.println("Skipping corrupted deadline task (missing date): " + Arrays.toString(parts));
            return null;
        }
        return new Deadline(parts[3], parts[4]);
    }

    /**
     * Creates an Event task from file parts.
     *
     * @param parts The parsed file line split into components.
     * @return An Event object, or null if invalid.
     */
    private static Task createEvent(String[] parts) throws LunaException {
        if (parts.length < 6) {
            System.out.println("Skipping corrupted event task (missing dates): " + Arrays.toString(parts));
            return null;
        }
        return new Event(parts[3], parts[4], parts[5]);
    }
}
