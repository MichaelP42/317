package org.lunar.rs2.network.packet;

import org.lunar.rs2.game.mob.player.Player;

/**
 * Represents a single packet handler for handling incoming packets.
 * 
 * @author Michael P
 *
 */
public interface PacketHandler {

	
	/**
	 * Handles the incoming packet.
	 * 
	 * @param player
	 *            The player to handle the packet for.
	 * @param packet
	 *            The incoming packet to handle.
	 */
	public void handle(Player player, Packet packet);
	
	/**
	 * Gets the incoming packet opcodes.
	 * @return The incoming packet opcodes.
	 */
	public int[] getOpcodes();
	
}
