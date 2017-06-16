package server.model.handlers.data;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import server.model.database.DBEntry;
import server.model.database.DBValue;
import server.model.database.Database;

/**
 * Reads the xml file and creates a database from it.
 * 
 * @author Chris
 *
 */
public class XMLReader extends DataReader {

	@Override
	public Database read(File file) {
		Database database = null;
		try {
			// Get root node of the xml file
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document root = dBuilder.parse(file);
			// Parse existing children as database entries
			if (root.hasChildNodes()) {
				database = new Database(parseEntries(root.getChildNodes(), 0));
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return database;
	}

	@Override
	public Database read(String text) {
		Database database = null;
		try {
			// Get root node of the xml file
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document root = dBuilder.parse(new InputSource(new StringReader(text)));
			// Parse existing children as database entries
			if (root.hasChildNodes()) {
				database = new Database(parseEntries(root.getChildNodes(), 0));
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		return database;
	}

	/**
	 * Parses all the xml elements. If the level is 0, it recursively calls this
	 * method in order to parse the elements of the root node.
	 * 
	 * @param nodes
	 * @param level
	 * @return
	 */
	private List<DBEntry> parseEntries(NodeList nodes, int level) {
		List<DBEntry> result = new ArrayList<DBEntry>();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node entry = nodes.item(i);
			// make sure it's element node
			if (entry.getNodeType() == Node.ELEMENT_NODE) {
				if (entry.hasChildNodes()) {
					if (level < 1) { // parsing root node
						result.addAll(parseEntries(entry.getChildNodes(), level + 1));
					} else {
						// parsing an entry of root node
						String name = entry.getNodeName();
						List<DBValue> values = new ArrayList<DBValue>();
						values.addAll(parseValues(entry.getChildNodes()));
						result.add(new DBEntry(name, values));
					}
				}
			}
		}
		return result;
	}

	/**
	 * Parses all the values in the entry. If the entry has multiple elements,
	 * it creates a nested DBValue object.
	 * 
	 * @param values
	 * @return
	 */
	private Collection<? extends DBValue> parseValues(NodeList values) {
		List<DBValue> result = new ArrayList<DBValue>();
		for (int i = 0; i < values.getLength(); i++) {
			Node value = values.item(i);
			if (value.getNodeType() == Node.ELEMENT_NODE) {
				String name = value.getNodeName();
				NodeList children = value.getChildNodes();
				DBValue newVal = null;
				if (value.hasChildNodes() && children.getLength() > 1) {
					List<DBValue> nested = new ArrayList<DBValue>();
					nested.addAll(parseValues(value.getChildNodes()));
					newVal = new DBValue(name, nested);
					if (value.hasAttributes()) {
						NamedNodeMap nodeMap = value.getAttributes();
						for (int j = 0; j < nodeMap.getLength(); j++) {
							Node node = nodeMap.item(j);
							newVal.setArray(node.getNodeValue().equals("true") ? true : false);
						}
					}
				} else {
					newVal = new DBValue(name, value.getTextContent());
				}
				result.add(newVal);
			}
		}
		return result;
	}

}
