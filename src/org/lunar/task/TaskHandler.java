package org.lunar.task;

import java.util.*;

import org.lunar.rs2.game.World;

/**
 * Handles implements of the {@link Task} interface.
 * 
 * @author Michael P
 *
 */
public class TaskHandler {
	
	/**
	 * The {@link TaskHandler} instance.
	 */
	private static final TaskHandler instance = new TaskHandler();

	/**
	 * A {@link List} of {@link Task} implements.
	 */
	private List<Task> tasks = new LinkedList<Task>();
	
	/**
	 * Adds the default task to the list.
	 */
	public void addDefaultTasks() {
		Task[] defaultTask = new Task[] {new World()};
		for (Task task : defaultTask) {
			if (task == null)
				continue;
			tasks.add(task);
		}
	}
	
	/**
	 * Pulses the task every 600 milliseconds on the main engine thread.
	 */
	public void pulse() {
		for (Iterator<Task> $it = tasks.iterator(); $it.hasNext();) {
			Task task = $it.next();
			if (task == null) {
				$it.remove();
				continue;
			}
			int delay = task.delay() + 1;
			if (delay-- == 0) {
				task.execute();
			}
		}
	}
	
	/**
	 * Gets the {@link TaskHandler} instance.
	 * @return The instance of the {@link TaskHandler} class.
	 */
	public static TaskHandler getInstance() {
		return instance;
	}
}
