package org.lunar.rs2.network;

import org.jboss.netty.channel.Channel;
import org.lunar.rs2.game.mob.player.Player;
import org.lunar.rs2.network.codec.Decoder;
import org.lunar.rs2.network.codec.Encoder;
import org.lunar.rs2.security.ISAACCipher;

/**
 * Manages a player login.
 * 
 * @author Michael P
 *
 */
public final class LoginManager {

	/**
	 * Configures a new {@link Player}.
	 * 
	 * @param channel
	 *            The channel of the player.
	 * @param name
	 *            The name of the player.
	 * @param pass
	 *            The pass of the player.
	 * @param lowmem
	 *            Is the player's client low-memory?
	 * @param in
	 *            The player's ISAACCipher for incoming packets.
	 * @param out
	 *            The player's ISAACCipher for outgoing packets.
	 */
	public static void configurePlayer(Channel channel, String name, String pass, boolean lowmem, ISAACCipher in, ISAACCipher out) {
		Player player = new Player(channel);
		player.setUsername(name);
		player.setPassword(pass);
		player.setLowmem(lowmem);
		player.setIn(in);
		player.setOut(out);
		player.getChannel().getPipeline().replace("decoder", "decoder", new Decoder(player));
		player.getChannel().getPipeline().addLast("encoder", new Encoder(player));
		player.login();
	}
	
}
