package org.lunar.rs2.game;

import org.lunar.rs2.game.mob.player.Player;
import org.lunar.rs2.game.utility.MobContainer;
import org.lunar.task.Task;

/**
 * The World class which implements the {@link Task} interface
 * for managing the updating of the rs2 game world. This class also
 * contains all registered game entity's.
 * 
 * @author Michael P
 *
 */
public class World implements Task {

	/**
	 * The container of registered {@link Player}s.
	 */
	private MobContainer<Player> players = new MobContainer<Player>(2000);
	
	/**
	 * Registers a new {@link Player} into the rs2 game world.
	 * 
	 * @param player
	 *            The {@link Player} to register.
	 * @return Can the {@link Player} be registered?
	 */
	public boolean register(Player player) {
		if (!players.add(player))
			return false;
		player.setSize(1);
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.lunar.task.Task#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.lunar.task.Task#delay()
	 */
	@Override
	public int delay() {
		return 0;
	}

}
