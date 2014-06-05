package org.lunar.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * The {@link ExceptionLogger} class handles
 * the logging of any exceptions sent within
 * the Lunar Source.
 * 
 * @author Michael P
 *
 */
public class ExceptionLogger {

	/**
	 * Constructs a new {@link ExceptionLogger}
	 * 
	 * @param loggingObject
	 *            The object to log.
	 */
	public ExceptionLogger(Object loggingObject) {
		this.loggingObject = loggingObject;
	}
	
	/**
	 * The object we're logging.
	 */
	private final Object loggingObject;
	
	/**
	 * Logs the Exception into a .LOG file.
	 * 
	 * @param ex
	 *            The {@link Exception} we're logging.
	 */
	public void logExcpetion(Exception ex) {
		try {
			final String path = "./data/exception_logs/"+ loggingObject.getClass().getName().replace(".java", "");
			File file = new File(path);
			if (!file.exists())
				file.mkdir();
			final BufferedWriter writer = new BufferedWriter(new FileWriter(path +"/"+ loggingObject.getClass().getSimpleName() +".log", true));
			writer.write("["+ Watch.getTime() +"] "+ ex.getStackTrace()[0].getClassName() +" : "+ ex.getStackTrace()[0].getMethodName() +" : "+ ex.getLocalizedMessage() +" @ line "+ ex.getStackTrace()[0].getLineNumber());
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
