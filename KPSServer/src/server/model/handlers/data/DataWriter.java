package server.model.handlers.data;

import server.model.database.Database;

/**
 * This class is responsible for writing operation of the data parser.
 * 
 * @author Chris
 *
 */
public abstract class DataWriter {

	/**
	 * Reads the database object and returns a string representation.
	 * 
	 * @param database
	 * @return
	 */
	public abstract String write(Database database);
}
