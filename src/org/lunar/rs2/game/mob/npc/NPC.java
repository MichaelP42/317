package org.lunar.rs2.game.mob.npc;

import org.lunar.rs2.game.mob.Mob;

/**
 * The NPC class is an extension of the {@link Mob} class which
 * represents a non-player-controlled {@link Mob}.
 * 
 * @author Michael P
 *
 */
public final class NPC extends Mob {

	/**
	 * Constructs a new non-player-controlled {@link Mob}.
	 * 
	 * @param id
	 *            The id of the NPC.
	 */
	public NPC(int id) {
		this.id = id;
	}
	
	/**
	 * The id of the NPC.
	 */
	private final int id;
	
	/**
	 * Gets the NPC's id.
	 * @return The id of the NPC.
	 */
	public int getId() {
		return id;
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
		return EntityType.NPC;
	}

}
