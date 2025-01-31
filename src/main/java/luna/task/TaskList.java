package luna.task;

import java.util.ArrayList;
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
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The index of the task to be deleted (0-based).
     * @throws LunaException If the index is out of bounds.
     */
    public void deleteTask(int index) throws LunaException {
        if (index < 0 || index >= tasks.size()) {
            throw new LunaException(LunaException.ErrorType.INVALID_TASK_NUMBER, "");
        }
        tasks.remove(index);
    }

    /**
     * Marks a task as completed at the specified index.
     *
     * @param index The index of the task to be marked as done (0-based).
     * @throws LunaException If the index is out of bounds.
     */
    public void markTask(int index) throws LunaException {
        if (index < 0 || index >= tasks.size()) {
            throw new LunaException(LunaException.ErrorType.INVALID_TASK_NUMBER, "");
        }
        tasks.get(index).mark();
    }

    /**
     * Unmarks a task (marks it as not completed) at the specified index.
     *
     * @param index The index of the task to be unmarked (0-based).
     * @throws LunaException If the index is out of bounds.
     */
    public void unmarkTask(int index) throws LunaException {
        if (index < 0 || index >= tasks.size()) {
            throw new LunaException(LunaException.ErrorType.INVALID_TASK_NUMBER, "");
        }
        tasks.get(index).unmark();
    }

    /**
     * Searches for tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching tasks.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The current list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Prints the task list to the console.
     */
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
