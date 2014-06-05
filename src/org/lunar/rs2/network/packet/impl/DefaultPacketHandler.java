package org.lunar.rs2.network.packet.impl;

import org.lunar.rs2.game.mob.player.Player;
import org.lunar.rs2.network.packet.Packet;
import org.lunar.rs2.network.packet.PacketHandler;

/**
 * The default/idle packet handler. This handles idle packets.
 * 
 * NOTE: This is different from the idle logout packet.
 * 
 * @author Michael P
 *
 */
public class DefaultPacketHandler implements PacketHandler {

	/*
	 * (non-Javadoc)
	 * @see org.lunar.rs2.network.packet.PacketHandler#handle(org.lunar.rs2.game.mob.player.Player, org.lunar.rs2.network.packet.Packet)
	 */
	@Override
	public void handle(Player player, Packet packet) {}

	/*
	 * (non-Javadoc)
	 * @see org.lunar.rs2.network.packet.PacketHandler#getOpcodes()
	 */
	@Override
	public int[] getOpcodes() {
		return new int[] { 0, 241, 86, 3, 77, 210, 78, 226 };
	}

}
