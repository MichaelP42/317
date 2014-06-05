package org.lunar.rs2.game;

/**
 * Represents a single entity in the rs2 game world.
 * 
 * Things which will extend this class: NPC's, player's, ground item's, object's,
 * and projectile's.
 * 
 * @author Michael P
 *
 */
public abstract class Entity {

	/**
	 * The location of the entity.
	 */
	protected Location location;
	
	/**
	 * Sets the entity's location.
	 * 
	 * @param location
	 *            The location to set the entity.
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/**
	 * Gets the location of the entity.
	 * @return The entity's location.
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * Gets the {@link EntityType} of the entity.
	 * @return The entity's {@link EntityType}.
	 */
	public abstract EntityType getEntityType();
	
	/**
	 * Represents the type of entity.
	 * 
	 * @author Michael P
	 *
	 */
	public enum EntityType {
		NPC, PLAYER, MOB, OBJECT, GROUND_ITEM, PROJECTILE
	}
	
}
