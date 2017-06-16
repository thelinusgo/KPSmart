package server.model.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the values within each entry of the database. If the value is
 * composite, it contains multiple DBValues.
 * 
 * @author Chris
 *
 */
public class DBValue {

	private String name;
	private String value;
	private boolean isComposite;
	private boolean isArray = false;
	private List<DBValue> fields;

	private DBValue(String name, boolean isComposite) {
		this.name = name;
		this.isComposite = isComposite;
	}

	/**
	 * Creates a raw value -- no nested fields
	 * 
	 * @param name
	 * @param value
	 */
	public DBValue(String name, String value) {
		this(name, false);
		this.value = value;
		this.fields = new ArrayList<DBValue>();
	}

	/**
	 * Creates a composite value -- has nested fields
	 * 
	 * @param name
	 * @param fields
	 */
	public DBValue(String name, List<DBValue> fields) {
		this(name, true);
		this.value = "";
		this.fields = fields;
	}

	// Getters

	public boolean isArray() {
		return isArray;
	}

	public boolean isComposite() {
		return isComposite;
	}

	public Object getValue() {
		return isComposite ? fields : value;
	}

	public String getName() {
		return name;
	}

	// Setters

	public void setArray(boolean isArray) {
		this.isArray = isArray;
	}
}
