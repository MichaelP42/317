package org.lunar.rs2.game.mob;

import org.lunar.rs2.game.Entity;

/**
 * The Mob class is an extension of the {@link Entity} class.
 * A mob represents an NPC or player in the rs2 game world.
 * 
 * @author Michael
 *
 */
public abstract class Mob extends Entity {
	
	/**
	 * The size of the mob.
	 */
	private int size;
	
	/**
	 * Sets the mob's size.
	 * 
	 * @param size
	 *            The size to set the mob.
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * Gets the size of the mob.
	 * @return The mob size.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Contains logic which is called to process the mob.
	 */
	public abstract void process();
	
	/**
	 * Contains logic which is called to reset the mob processing.
	 */
	public abstract void reset();
	
	/*
	 * (non-Javadoc)
	 * @see org.lunar.rs2.game.Entity#getEntityType()
	 */
	@Override
	public EntityType getEntityType() {
		return EntityType.MOB;
	}

}
