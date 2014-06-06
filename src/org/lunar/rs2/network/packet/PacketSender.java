package org.lunar.rs2.network.packet;

import org.lunar.rs2.game.mob.player.Player;

/**
 * Contains methods which sends packets to the client.
 * 
 * @author Michael P
 *
 */
public final class PacketSender {

	/**
	 * Constructs a new packet sender.
	 * 
	 * @param player
	 *            The player.
	 */
	public PacketSender(Player player) {
		this.player = player;
	}
	
	/**
	 * The player we're sending the packets for.
	 */
	private final Player player;
	
	/**
	 * Sends the login response.
	 * 
	 * @param response
	 *           The response code.
	 */
	public void sendLoginResponse(int response) {
		player.write(new PacketBuilder().put((byte) response).put((byte) 3).put((byte) 0).toPacket());
	}
	
	/**
	 * Sends all the needed packets on login.
	 */
	public void configureLogin() {
		
	}

}
