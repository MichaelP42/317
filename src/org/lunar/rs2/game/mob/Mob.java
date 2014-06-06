package org.lunar.rs2.game.mob;

import org.lunar.rs2.game.Entity;
import org.lunar.rs2.game.Location;
import org.lunar.rs2.game.mob.player.Player;
import org.lunar.rs2.network.packet.PacketSender;

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
	 * The last known location of the mob.
	 */
	private Location lastKnownLocation;
	
	/**
	 * The packet sender of the mob.
	 */
	protected PacketSender packetSender;
	
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
	 * Sets the last known location of the mob.
	 * 
	 * @param lastKnownLocation
	 *            The last known location to set the mob.
	 */
	public void setLastKnownLocation(Location lastKnownLocation) {
		this.lastKnownLocation = lastKnownLocation;
	}
	
	/**
	 * Gets the size of the mob.
	 * @return The mob size.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Gets the mob's last known location.
	 * @return The last known location of the mob.
	 */
	public Location getLastKnownLocation() {
		return lastKnownLocation;
	}
	
	/**
	 * Gets the packet sender for the mob.
	 * @return If the {@link EntityType} is a player {@link PacketSender}, else null.
	 */
	public PacketSender getPacketSender() {
		if (packetSender == null && getEntityType().equals(EntityType.PLAYER)) {
			packetSender = new PacketSender((Player) this);
		}
		return packetSender;
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
