package org.lunar;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.lunar.utility.ExceptionLogger;

/**
 * The main class of the Lunar server.
 * 
 * @author Michael P
 *
 */
public class Server {
	
	/**
	 * The {@link ExceptionLogger} of the Lunar server.
	 */
	private static final ExceptionLogger exceptionLogger = new ExceptionLogger(new Server());

	/**
	 * The {@link Logger} of the {@link Server} class.
	 */
	private static final Logger logger = Logger.getLogger(Server.class.getName());
	
	/**
	 * Starts the java application.
	 * 
	 * @param args
	 *            The arguments.
	 */
	public static void main(String...strings) {
		if (strings.length < 1 && strings.length > 1) {
			logger.warning("Incorrect useage! Usage: <port>");
			return;
		}
		logger.info("Booting Lunar...");
		long start = System.currentTimeMillis();
		try {
			
			// Configure the game world.
			// TODO Configure the world... Need world class.
			
			// Configure & bind the bootstrap.
			int port = Integer.parseInt(strings[0]);
			NioServerSocketChannelFactory factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
			ServerBootstrap bootstrap = new ServerBootstrap(factory);
			bootstrap.setPipelineFactory(null);
			bootstrap.bind(new InetSocketAddress(port));
			
			// Power the engine.
			Engine.power();
			
		} catch (Exception ex) {
			exceptionLogger.logExcpetion(ex);
		} finally {
			long elapsed = System.currentTimeMillis() - start;
			logger.info("Lunar booted! Took "+ (int) elapsed +" milliseconds!");
		}
	}

}
