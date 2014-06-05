package org.lunar.task;

import java.util.*;

/**
 * Handles implements of the {@link Task} interface.
 * 
 * @author Michael P
 *
 */
public class TaskHandler {

	/**
	 * A {@link List} of {@link Task} implements.
	 */
	private List<Task> tasks = new LinkedList<Task>();
	
	/**
	 * Adds the default task to the list.
	 */
	public void addDefaultTasks() {
		Task[] defaultTask = new Task[] {};
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
			int delay = task.delay();
			if (delay-- == 0) {
				task.execute();
			}
		}
	}
}
