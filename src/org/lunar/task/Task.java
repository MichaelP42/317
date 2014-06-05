package org.lunar.task;

/**
 * An interface which describes a single game task.
 * 
 * @author Michael P
 *
 */
public interface Task {

	/**
	 * Calls logic when the task's ticks are up.
	 */
	void execute();
	
	/**
	 * Gets the delay of the task.
	 * @return The task's delay.
	 */
	int delay();
	
}
