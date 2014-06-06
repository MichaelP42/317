package org.lunar.rs2.network.codec;

import java.security.SecureRandom;
import java.util.logging.Logger;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.lunar.rs2.network.LoginManager;
import org.lunar.rs2.security.ISAACCipher;
import org.lunar.rs2.utility.Misc;

public class LoginDecoder extends FrameDecoder {

	private int stage = 0;
	
	private static final Logger logger = Logger.getLogger(LoginDecoder.class.getName());
	
	/**
	 * Initial login response.
	 */
	private static final byte[] INITIAL_RESPONSE = new byte[] {
		0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0
	};
	
	/*
	 * (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.frame.FrameDecoder#decode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, org.jboss.netty.buffer.ChannelBuffer)
	 */
	@Override
	protected Object decode(ChannelHandlerContext arg0, Channel channel, ChannelBuffer buffer) throws Exception {
		if (!channel.isConnected())
			return null;
		switch (stage) {
			case 0:
				if (buffer.readableBytes() >= 1) {
					int opcode = buffer.readUnsignedByte();
					System.out.print(opcode);
					switch (opcode) {
						case 14:
							stage = 1;
							break;
					}
				}
				return null;
			case 1:
				if (buffer.readableBytes() >= 1) {
					buffer.readUnsignedByte();
					ChannelBuffer out2 = ChannelBuffers.dynamicBuffer();
					out2.writeBytes(INITIAL_RESPONSE);
					out2.writeByte(0);
					out2.writeLong(new SecureRandom().nextLong());
					channel.write(out2);
					stage = 2;
				}
				return null;
			case 2:
				System.out.print("stage 1");
				if (buffer.readableBytes() >= 2) {
					int request = buffer.readByte();
					if (request != 16 && request != 18) {
						logger.warning("Incorrect login request! Request sent = "+ request);
						channel.close();
						return null;
					}
					int blockLength = buffer.readUnsignedByte();
					@SuppressWarnings("unused")
					int loginEncryptSize = blockLength - (36 + 1 + 1 + 2);
					if (buffer.readableBytes() < blockLength) {
						logger.warning("Incorrect readable bytes! Readable bytes = "+ buffer.readableBytes());
						channel.close();
						return null;
					}
					int magic = buffer.readUnsignedByte();
					if (magic != 255) {
						logger.warning("Incorrect magic id! Magic = "+ magic);
						channel.close();
						return null;
					}
					int version = buffer.readShort();
					if (version != 317) {
						logger.warning("Incorrect protocol version! Version = "+ version);
						channel.close();
						return null;
					}
					boolean lowmem = buffer.readByte() == 1;
					for (int i = 0; i < 9; i++)
						buffer.readInt(); // crc
					buffer.readUnsignedByte();
					int rsaCode = buffer.readByte();
					if (rsaCode != 10) {
						logger.warning("Incorrect rsa code! Code = "+ rsaCode);
						channel.close();
						return null;
					}
					long clientSessionKey = buffer.readLong();
					long serverSessionKey = buffer.readLong();
					final int[] isaacSeed = { (int) (clientSessionKey >> 32), (int) clientSessionKey, (int) (serverSessionKey >> 32), (int) serverSessionKey };
					final ISAACCipher in = new ISAACCipher(isaacSeed);
					for (int i = 0; i < isaacSeed.length; i++)
						isaacSeed[i] += 50;
					final ISAACCipher out = new ISAACCipher(isaacSeed);
					buffer.readInt();
					String name = Misc.formatPlayerName(Misc.getRS2String(buffer));
					String pass = Misc.getRS2String(buffer);
					LoginManager.configurePlayer(channel, name, pass, lowmem, in, out);
					return null;
				}
				return null;
		}
		return null;
	}

}
