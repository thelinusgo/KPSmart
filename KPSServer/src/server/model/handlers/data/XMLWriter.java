package server.model.handlers.data;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import server.model.database.DBEntry;
import server.model.database.DBValue;
import server.model.database.Database;

public class XMLWriter extends DataWriter {

	@Override
	public String write(Database database) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.newDocument();
			// Create root element
			Element root = doc.createElement("root");
			// Write the database to the document
			parseDatabase(root, doc, database);
			// Append the root to the document
			doc.appendChild(root);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		String result = doc == null ? "" : write(doc);
		return result == null ? "" : result;
	}

	private String write(Document doc) {
		TransformerFactory tf = TransformerFactory.newInstance();
		String output = null;
		try {
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			output = writer.toString();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return output;
	}

	// Parsing the Database methods

	private void parseDatabase(Element root, Document doc, Database database) {
		for (DBEntry entry : database.getEntries()) {
			Element e = doc.createElement(entry.getName());
			parseEntry(e, doc, entry);
			root.appendChild(e);
		}
	}

	private void parseEntry(Element entryEle, Document doc, DBEntry entry) {
		for (DBValue value : entry.getValues()) {
			Element e = doc.createElement(value.getName());
			parseValue(e, doc, value);
			entryEle.appendChild(e);
		}
	}

	private void parseValue(Element valueEle, Document doc, DBValue value) {
		if (!value.isComposite()) {
			valueEle.appendChild(doc.createTextNode((String) value.getValue()));
		} else {
			@SuppressWarnings("unchecked")
			List<DBValue> fields = (ArrayList<DBValue>) value.getValue();
			for (DBValue field : fields) {
				Element fieldEle = doc.createElement(field.getName());
				parseValue(fieldEle, doc, field);
				valueEle.appendChild(fieldEle);
			}
		}
	}
}
