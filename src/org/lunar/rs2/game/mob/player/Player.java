package org.lunar.rs2.game.mob.player;

import org.jboss.netty.channel.Channel;
import org.lunar.rs2.game.mob.Mob;

/**
 * The player class is an extension of the {@link Mob} class which represents a
 * player-controlled {@link Mob}.
 * 
 * @author Michael P
 *
 */
public final class Player extends Mob {

	/**
	 * Constructs a new player-controlled {@link Mob}.
	 * 
	 * @param channel
	 *            The player's {@link Channel}.
	 */
	public Player(Channel channel) {
		this.channel = channel;
	}
	
	/**
	 * The player's {@link Channel}.
	 */
	private final Channel channel;
	
	/**
	 * Gets the {@link Channel} of the player.
	 * @return The player's {@link Channel}.
	 */
	public Channel getChannel() {
		return channel;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.lunar.rs2.game.mob.Mob#process()
	 */
	@Override
	public void process() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see org.lunar.rs2.game.mob.Mob#reset()
	 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
	
	/*
	 * (non-Javadoc)
	 * @see org.lunar.rs2.game.mob.Mob#getEntityType()
	 */
	@Override
	public EntityType getEntityType() {
		return EntityType.PLAYER;
	}

}
