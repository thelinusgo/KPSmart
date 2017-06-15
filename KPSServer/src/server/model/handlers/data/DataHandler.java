package server.model.handlers.data;

import java.io.File;

import server.model.database.Database;

public class DataHandler {

	private Database database;

	public DataHandler() {
		this.database = new Database(); // create empty database
	}

	public Database getDatabase() {
		return database;
	}

	public void read(DataParser parser, String text, boolean isFile) {
		this.database = isFile ? parser.read(new File(text)) : parser.read(text);
	}

	public String write(DataParser parser) {
		return parser.write(database);
	}
}
