package server.model.handlers.data;

import java.io.File;

import server.model.database.Database;

public class DataHandler {

	private Database database;

	public DataHandler() {
		this.database = new Database(); // create empty database
	}

	public void read(DataParser parser, String file) {
		this.database = parser.read(new File(file));
	}

	public String write(DataParser parser) {
		return parser.write(database);
	}
}
