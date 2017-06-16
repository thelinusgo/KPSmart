package server.model.handlers.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import server.model.database.DBEntry;
import server.model.database.DBValue;
import server.model.database.Database;

public class JSONReader extends DataReader {

	@Override
	public Database read(File file) {
		Database database = null;
		// TODO Auto-generated method stub
		return database;
	}

	@Override
	public Database read(String text) {
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		Database database = null;

		try {
			JsonNode root = mapper.readTree(text);
			Iterator<Entry<String, JsonNode>> fieldsIterator = root.getFields();
			if (fieldsIterator.hasNext()) {
				database = new Database(parseEntries(fieldsIterator));
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return database;
	}

	private List<DBEntry> parseEntries(Iterator<Entry<String, JsonNode>> jsonEntries) {
		List<DBEntry> entries = new ArrayList<DBEntry>();
		while (jsonEntries.hasNext()) {
			Entry<String, JsonNode> jsonEntry = jsonEntries.next();
			String name = jsonEntry.getKey();
			JsonNode value = jsonEntry.getValue();
			List<DBValue> values = new ArrayList<DBValue>();
			if (value.isArray()) {
				for (final JsonNode temp2 : value) {
					values.addAll(parseValues(temp2, name));
				}
			} else {
				values.addAll(parseValues(value, name));
			}
			entries.add(new DBEntry(name, values));
		}
		return entries;
	}

	private List<DBValue> parseValues(JsonNode value, String parentName) {
		List<DBValue> result = new ArrayList<DBValue>();
		if (value.isArray()) {
			String compName = parentName + "-e";
			List<DBValue> compVal = new ArrayList<DBValue>();
			for (final JsonNode compNode : value) {
				compVal.addAll(parseValues(compNode, compName));
			}
			result.add(new DBValue(compName, compVal));
		} else {
			result.addAll(parseValue(value.toString()));
		}
		return result;
	}

	private List<DBValue> parseValue(String text) {
		List<DBValue> values = new ArrayList<DBValue>();
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		try {
			JsonNode root = mapper.readTree(text);
			Iterator<Entry<String, JsonNode>> iterator = root.getFields();
			if (root.isObject()) {
				List<DBValue> vals = new ArrayList<DBValue>();
				while (iterator.hasNext()) {
					Entry<String, JsonNode> field = iterator.next();
					String name = field.getKey();
					JsonNode node = field.getValue();
					if (node.isObject() || node.isArray()) {
						List<DBValue> vals2 = parseValues(node, name);
						vals.add(new DBValue(name, vals2));
					} else {
						vals.add(new DBValue(name, node.toString().replaceAll("\"", "")));
					}
				}
				values.add(new DBValue("entry", vals));
			} else {
				while (iterator.hasNext()) {
					Entry<String, JsonNode> field = iterator.next();
					String name = field.getKey();
					JsonNode node = field.getValue();
					if (node.isObject() || node.isArray()) {
						List<DBValue> vals = parseValues(node, name);
						values.add(new DBValue(name, vals));
					} else {
						values.add(new DBValue(name, node.toString().replaceAll("\"", "")));
					}
				}

			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return values;
	}

}
