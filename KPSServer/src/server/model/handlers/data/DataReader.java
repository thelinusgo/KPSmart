package server.model.handlers.data;

import java.io.File;

import server.model.database.Database;

/**
 * This class is responsible for reading the file in the data parser.
 * 
 * @author Chris
 *
 */
public abstract class DataReader {

	/**
	 * Reads the given file parameter and returns the database object
	 * representation.
	 * 
	 * @param file
	 * @return
	 */
	public abstract Database read(File file);
}
