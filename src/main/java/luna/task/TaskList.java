package luna.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import luna.LunaException;

/**
 * Manages a list of tasks, providing operations to add, delete, mark, and unmark tasks.
 */
public class TaskList {
    /**
     * List storing all tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list should not be null";
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     * @return A message confirming the addition.
     */
    public String addTask(Task task) {
        assert task != null : "Task being added should not be null";

        tasks.add(task);
        return "Got it~ I've added this task:\n" + task
                       + "\nNow you have " + tasks.size() + " tasks in the list!!";
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The index of the task to be deleted (0-based).
     * @return The deleted task.
     * @throws LunaException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws LunaException {
        if (index < 0 || index >= tasks.size()) {
            throw new LunaException(LunaException.ErrorType.INVALID_TASK_NUMBER, "");
        }
        return tasks.remove(index);
    }

    /**
     * Marks a task as completed at the specified index.
     *
     * @param index The index of the task to be marked as done (0-based).
     * @return A message confirming the marking.
     * @throws LunaException If the index is out of bounds.
     */
    public String markTask(int index) throws LunaException {
        if (index < 0 || index >= tasks.size()) {
            throw new LunaException(LunaException.ErrorType.INVALID_TASK_NUMBER, "");
        }
        tasks.get(index).mark();
        return "Yay!~ I've marked this task as done:\n" + tasks.get(index);
    }

    /**
     * Unmarks a task (marks it as not completed) at the specified index.
     *
     * @param index The index of the task to be unmarked (0-based).
     * @return A message confirming the unmarking.
     * @throws LunaException If the index is out of bounds.
     */
    public String unmarkTask(int index) throws LunaException {
        if (index < 0 || index >= tasks.size()) {
            throw new LunaException(LunaException.ErrorType.INVALID_TASK_NUMBER, "");
        }
        tasks.get(index).unmark();
        return "OKi~ I've marked this task as not done yet:\n" + tasks.get(index);
    }

    /**
     * Searches for tasks that contain the given keyword in their description.
     *
     * @param keywords The keywords to search for.
     * @return A message listing the found tasks.
     */
    public String findTasks(String... keywords) {
        List<Task> matchingTasks = tasks.stream()
                                           .filter(task -> {
                                               String description = task.getDescription().toLowerCase();
                                               return Arrays.stream(keywords).anyMatch(description::contains);
                                           })
                                           .toList();

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found!";
        }

        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
        }

        return result.toString().trim();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The current list of tasks.
     */
    public ArrayList<Task> getTasks() {
        assert tasks != null : "Task list should never be null";
        return tasks;
    }

    /**
     * Returns the task list as a formatted string.
     *
     * @return A string representation of all tasks in the list.
     */
    public String printList() {
        if (tasks.isEmpty()) {
            return "Your task list is empty!";
        }

        StringBuilder result = new StringBuilder("Here are your tasks:\n");

        tasks.stream()
                .map(task -> (tasks.indexOf(task) + 1) + ". " + task)
                .reduce((a, b) -> a + "\n" + b)
                .ifPresent(result::append);

        return result.toString();
    }
}
