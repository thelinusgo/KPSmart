package server.model.database;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Chris
 *
 */
public class Database {

	private List<DBEntry> entries;

	/**
	 * Creates a database with an empty list of entries
	 */
	public Database() {
		this(new ArrayList<DBEntry>());
	}

	/**
	 * Creates a database with the given list of entries
	 * 
	 * @param entries
	 */
	public Database(List<DBEntry> entries) {
		this.entries = entries;
	}

	public List<DBEntry> getEntries() {
		return entries;
	}
}
