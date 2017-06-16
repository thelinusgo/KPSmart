package server.tests;

import server.model.handlers.data.DataHandler;
import server.model.handlers.data.DataParser;
import server.model.handlers.data.JSONReader;
import server.model.handlers.data.JSONWriter;
import server.model.handlers.data.XMLReader;
import server.model.handlers.data.XMLWriter;

public class DataParserTests {

	public static void test01_xml_read_rawValuesOnly() {
		DataParser parser = createXMLParser();
		DataHandler handler = new DataHandler();
		handler.read(parser, "./tests/test_dat_01.xml", true);
	}

	public static void test02_xml_read_compositeValues() {
		DataParser parser = createXMLParser();
		DataHandler handler = new DataHandler();
		handler.read(parser, "./tests/test_dat_02.xml", true);
	}

	public static void test03_xml_write_database() {
		DataParser parser = createXMLParser();
		DataHandler handler = new DataHandler();
		handler.read(parser, "./tests/test_dat_02.xml", true);
		String output = handler.write(parser);
		System.out.println(output);
	}

	public static void test04_json_read_rawValue() {
		String jsonTest = "{\"key\" : \"value\"}";
		DataParser parser = createJSONParser();
		DataHandler handler = new DataHandler();
		handler.read(parser, jsonTest, false);
	}

	public static void test05_json_read_arrayValues() {
		String jsonTest = "{\"entries\": [{\"name\" : \"something\", \"field\" : \"something\"}, {\"name\" : \"subValue\", \"field\" : \"something\"}]}";
		DataParser parser = createJSONParser();
		DataHandler handler = new DataHandler();
		handler.read(parser, jsonTest, false);
	}

	// Test Helpers

	private static DataParser createXMLParser() {
		return new DataParser(new XMLReader(), new XMLWriter());
	}

	private static DataParser createJSONParser() {
		return new DataParser(new JSONReader(), new JSONWriter());
	}

	public static void main(String[] args) {
		// test01_xml_read_rawValuesOnly();
		// test02_xml_read_compositeValues();
		// test03_xml_write_database();
		// test04_json_read_rawValue();
		test05_json_read_arrayValues();
	}
}
