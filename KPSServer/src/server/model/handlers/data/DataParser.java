package server.model.handlers.data;

import java.io.File;

import server.model.database.Database;

/**
 * This class supports the read and write functions. The read function reads the
 * specified file and creates a Database object from it. The write function
 * reads the database object and then transforms it into a string of a specific
 * format.
 * 
 * @author Chris
 *
 */
public class DataParser {

	private DataReader reader; // responsible for reading
	private DataWriter writer; // responsible for writing

	public DataParser(DataReader reader, DataWriter writer) {
		this.reader = reader;
		this.writer = writer;
	}

	/**
	 * Reads the file parameter and creates a database object from it.
	 * 
	 * @param file
	 * @return Database object
	 */
	public Database read(File file) {
		return reader.read(file);
	}

	/**
	 * Reads the text parameter and creates a database object from it.
	 * 
	 * @param text
	 * @return
	 */
	public Database read(String text) {
		return reader.read(text);
	}

	/**
	 * Reads the database paramter and then returns a string representation of
	 * the database.
	 * 
	 * @param database
	 * @return
	 */
	public String write(Database database) {
		return writer.write(database);
	}
}
