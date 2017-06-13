package server.model.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents rows inside a database.
 * 
 * @author Chris
 *
 */
public class DBEntry {

	private String name;
	private List<DBValue> values;

	public DBEntry(String name) {
		this(name, new ArrayList<DBValue>());
	}

	public DBEntry(String name, List<DBValue> values) {
		this.name = name;
		this.values = values;
	}

	// Getters

	public String getName() {
		return name;
	}

	public List<DBValue> getValues() {
		return values;
	}
}
