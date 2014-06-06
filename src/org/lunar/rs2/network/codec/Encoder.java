package org.lunar.rs2.network.codec;

import java.nio.ByteBuffer;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.lunar.rs2.game.mob.player.Player;
import org.lunar.rs2.network.packet.Packet;

/**
 * Encodes packets sent to the client.
 * 
 * @author Michael P
 *
 */
public class Encoder extends OneToOneEncoder {

	/**
	 * Constructs a new Encoder.
	 * 
	 * @param player
	 *            The player to construct the encoder for.
	 */
	public Encoder(Player player) {
		this.player = player;
	}
	
	/**
	 * The player to encode for.
	 */
	private final Player player;
	
	/*
	 * (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@SuppressWarnings("incomplete-switch")
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel, Object message) throws Exception {
		if (message instanceof ByteBuffer) {
			return ChannelBuffers.copiedBuffer((ByteBuffer) message);
		} else if (message instanceof Packet) {
			Packet packet = (Packet) message;
			if (packet.isRaw()) {
				System.out.print("sent raw packet");
				return ChannelBuffers.copiedBuffer((ByteBuffer) packet.getPayload().flip());
			}
			ByteBuffer buffer = ByteBuffer.allocate(packet.getPayload().position() + 3);
			byte opcode = (byte) packet.getOpcode();
			opcode += player.getOut().getNextValue();
			buffer.put(opcode);
			switch (packet.getType()) {
				case VARIABLE:
					buffer.put((byte) packet.getPayload().position());
					break;
				case VARIABLE_SHORT:
					buffer.putShort((short) packet.getPayload().position());
					break;
			}
			buffer.put((ByteBuffer) packet.getPayload().flip());
			return ChannelBuffers.copiedBuffer((ByteBuffer) buffer.flip());
		} else {
			System.err.println("Unhanlded packet type for encoder.");
			return null;
		}
	}
}
