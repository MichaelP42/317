package org.lunar.rs2.game.mob.player;

import java.util.LinkedList;
import java.util.Queue;

import org.jboss.netty.channel.Channel;
import org.lunar.rs2.game.mob.Mob;
import org.lunar.rs2.network.packet.Packet;
import org.lunar.rs2.network.packet.PacketManager;

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
