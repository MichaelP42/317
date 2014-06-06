package org.lunar.rs2.game.mob.player;

import java.util.LinkedList;
import java.util.Queue;

import org.jboss.netty.channel.Channel;
import org.lunar.rs2.game.World;
import org.lunar.rs2.game.mob.Mob;
import org.lunar.rs2.network.packet.Packet;
import org.lunar.rs2.network.packet.PacketManager;
import org.lunar.rs2.security.ISAACCipher;

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
	 * A {@link Queue} of packets ready to be read.
	 */
	private Queue<Packet> packets = new LinkedList<Packet>();
	
	/**
	 * The username of the player.
	 */
	private String username;
	
	/**
	 * The password of the player.
	 */
	private String password;
	
	/**
	 * Is the player using a low-memory client?
	 */
	private boolean lowmem;
	
	/**
	 * The player's {@link ISAACCipher} for incoming & outgoing packets.
	 */
	private ISAACCipher in, out;
	
	/**
	 * Adds a packet to the queue.
	 * 
	 * @param packet
	 *            The packet to add.
	 */
	public void addPacket(Packet packet) {
		synchronized (packets) {
			packets.add(packet);	
		}
	}
	
	/**
	 * Handles all the packets in the queue.
	 * 
	 * @param player
	 *            The player to handle the packets for.
	 */
	public void handlePackets(Player player) {
		Packet packet = null;
		while ((packet = packets.poll()) != null) {
			PacketManager.handlePacket(player, packet);
		}
	}
	
	/**
	 * Writes a packet to the player's channel.
	 * 
	 * @param packet
	 *            The packet object to write.
	 */
	public void write(Object packet) {
		channel.write(packet);
	}
	
	/**
	 * Does final checking and calls the login configure from the player's packet sender.
	 */
	public void login() {
		// TODO Load the player save file.
		if (!checkLogin()) {
			return;
		}
		System.out.println("configure login");
		packetSender.configureLogin();
		World.getWorld().register(this);
	}
	
	/**
	 * Checks the login and sends the response packet.
	 * @return If the player can login {@code true};
	 * if not {@code false}.
	 */
	public boolean checkLogin() {
		packetSender.sendLoginResponse(2);
		return true;
	}
	
	/**
	 * Sets the player's username.
	 * 
	 * @param username
	 *            the username to set the player.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Sets the player's password.
	 * 
	 * @param password
	 *            The password to set the player.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Sets the player's lowmem flag.
	 * 
	 * @param lowmem
	 *            The lowmem flag.
	 */
	public void setLowmem(boolean lowmem) {
		this.lowmem = lowmem;
	}
	
	/**
	 * Sets the player's ISAACCipher for incoming packets.
	 * 
	 * @param in
	 *            The  ISAACCipher.
	 */
	public void setIn(ISAACCipher in) {
		this.in = in;
	}
	
	/**
	 * Sets the player's ISAACCipher for outgoing packets.
	 * 
	 * @param out
	 *            The ISAACCipher.
	 */
	public void setOut(ISAACCipher out) {
		this.out = out;
	}
	
	/**
	 * Gets the {@link Channel} of the player.
	 * @return The player's {@link Channel}.
	 */
	public Channel getChannel() {
		return channel;
	}
	
	/**
	 * Gets the username.
	 * @return The username of the player.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Gets the password of the player.
	 * @return The player's password.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Is the player's client low-memory?
	 * @return If so {@code true};
	 * if not {@code false}.
	 */
	public boolean isLowmem() {
		return lowmem;
	}
	
	/**
	 * Gets the player's incoming ISAACCipher.
	 * @return The player's ISAACCipher for incoming packets.
	 */
	public ISAACCipher getIn() {
		return in;
	}
	
	/**
	 * Gets the player's ISAACCipher for outgoing packets.
	 * @return The ISAACCipher for outgoing packets.
	 */
	public ISAACCipher getOut() {
		return out;
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
