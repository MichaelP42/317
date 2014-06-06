package org.lunar.rs2.network.packet;

import org.lunar.rs2.game.mob.player.Player;
import org.lunar.rs2.network.packet.Packet.Type;

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
		PacketBuilder out = new PacketBuilder();
		out.put((byte) response);
		out.put((byte) 0);
		out.put((byte) 0);
        player.write(out.toPacket());
	}
	
	/**
	 * Sends all the needed packets on login.
	 */
	public void configureLogin() {
		sendPlayerDetails();
		sendMapRegion();
		sendCameraReset();
		sendMessage("Welcome to Lunar.");
	}
	
	/**
	 * Sends a map region.
	 */
	public void sendMapRegion() {
		player.setLastKnownLocation(player.getLocation());
		PacketBuilder out = new PacketBuilder(73);
		out.putShortA(player.getLocation().getRegionX() + 6);
		out.putShort(player.getLocation().getRegionY() + 6);
		player.write(out.toPacket());
	}
	
	/**
	 * Resets a player's camera.
	 */
	public void sendCameraReset() {
		player.write(new PacketBuilder(107).toPacket());
	}
	
	/**
	 * Sends a player's details.
	 */
	public void sendPlayerDetails() {
		PacketBuilder out = new PacketBuilder(249);
		out.putByteA(1); // world type - 0 = free, 1 = members
		out.putLEShortA(player.getIndex());
		player.write(out.toPacket());
	}
	
	/**
	 * Sends a message to the player's chat-box, such as "Welcome to Lunar.".
	 * 
	 * @param message
	 *            The message to send.
	 */
	public void sendMessage(String message) {
		PacketBuilder out = new PacketBuilder(253, Type.VARIABLE);
		out.putRS2String(message);
		player.write(out.toPacket());
	}

}
