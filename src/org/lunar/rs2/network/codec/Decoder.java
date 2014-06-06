package org.lunar.rs2.network.codec;

import java.nio.ByteBuffer;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.lunar.Constants;
import org.lunar.rs2.game.mob.player.Player;
import org.lunar.rs2.network.packet.Packet;
import org.lunar.rs2.network.packet.Packet.Type;

public class Decoder extends FrameDecoder {
	
	private final Player player;
	
	public Decoder(Player player) {
		this.player = player;
	}

	/*
	 * (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.frame.FrameDecoder#decode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, org.jboss.netty.buffer.ChannelBuffer)
	 */
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
		if (buffer.readableBytes() > 0) {
			int id = (buffer.readUnsignedByte() - player.getIn().getNextValue()) & 0xFF;
			int size = Constants.PACKET_SIZES[id];
			if(size == -1) {
				size = buffer.readByte() & 0xff;
			}
			if(size < 0) {
				size = buffer.readUnsignedByte();
			}
			if (buffer.readableBytes() >= size) {
				final byte[] data = new byte[size];
				buffer.readBytes(data);
				final ChannelBuffer payload = ChannelBuffers.buffer(size);
				payload.writeBytes(data);
				try {
					player.addPacket(new Packet(id, Type.FIXED, ByteBuffer.wrap(data)));
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}