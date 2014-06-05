package org.lunar.rs2.network.packet;

import java.io.File;
import java.util.HashMap;

import org.lunar.rs2.game.mob.player.Player;
import org.lunar.utility.ExceptionLogger;


/**
 * Manages incoming packets.
 * 
 * @author Michael P
 *
 */
public class PacketManager {
	
	/**
	 * The {@link ExceptionLogger}.
	 */
	private static final ExceptionLogger exceptionLogger = new ExceptionLogger(new PacketManager());

	/**
	 * A mapping of packet handlers.
	 */
	private static final HashMap<Integer, PacketHandler> handlers = new HashMap<Integer, PacketHandler>();
	
	/**
	 * Loads the packet handlers.
	 */
	public static void loadPackets() {
		File[] files = new File("./src/org/lunar/rs2/network/packet/impl/").listFiles();
        for (File f : files) {
            if (f == null) {
                continue;
            }
            try {
            	Object object = Class.forName("org.lunar.rs2.network.packet.impl." + f.getName().replace(".java", "")).newInstance();
                if (object instanceof PacketHandler) {
                	PacketHandler handler = (PacketHandler) object;
                	for (int i : handler.getOpcodes())
                		handlers.put(i, handler);
                }	
            } catch (Exception ex) {
            	exceptionLogger.logExcpetion(ex);
            }
        }
	}
	
	/**
	 * Handles incoming packets.
	 * 
	 * @param player
	 *            The player to handle the packet for.
	 * @param packet
	 *            The incoming packet to handle.
	 */
	public static void handlePacket(Player player, Packet packet) {
		PacketHandler handler = handlers.get(packet.getOpcode());
		if (handler == null) {
			return;	
		}
		if (packet.getOpcode() <= 0) {
			return;
		}
		handler.handle(player, packet);
	}
	
}
