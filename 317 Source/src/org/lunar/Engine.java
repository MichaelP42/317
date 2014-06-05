package org.lunar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The main engine of the Lunar server.
 * 
 * @author Michael P
 * 
 */
public final class Engine implements Runnable {

    /**
     * The {@link ScheduledExecutorService} of the engine.
     */
    private static final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    /**
     * The engine running flag.
     */
    private static boolean running;

    /**
     * Powers the engine.
     */
    public static void power() {
        if (running)
            throw new IllegalArgumentException("The engine has already been powered!");
        running = true;
        service.scheduleAtFixedRate(new Engine(), 0, 600, TimeUnit.MILLISECONDS);
    }

    /**
     * Shuts off the engine.
     */
    public static void shutoff() {
        running = false;
        service.shutdown();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        if (!running) {
            service.shutdownNow();
            return;
        }
        // TODO Handle processing.
    }

}
