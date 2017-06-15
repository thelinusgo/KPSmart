package server.tests;

import server.model.handlers.data.DataHandler;
import server.model.handlers.data.DataParser;
import server.model.handlers.data.XMLReader;
import server.model.handlers.data.XMLWriter;

public class DataParserTests {

	public static void test01_xml_read_rawValuesOnly() {
		DataParser parser = new DataParser(new XMLReader(), new XMLWriter());
		DataHandler handler = new DataHandler();
		handler.read(parser, "./tests/test_dat_01.xml", true);
	}

	public static void test02_xml_read_compositeValues() {
		DataParser parser = new DataParser(new XMLReader(), new XMLWriter());
		DataHandler handler = new DataHandler();
		handler.read(parser, "./tests/test_dat_02.xml", true);
	}

	public static void test03_xml_write_database() {
		DataParser parser = new DataParser(new XMLReader(), new XMLWriter());
		DataHandler handler = new DataHandler();
		handler.read(parser, "./tests/test_dat_02.xml", true);
		String output = handler.write(parser);
		System.out.println(output);
	}

	public static void main(String[] args) {
		// test01_xml_read_rawValuesOnly();
		//test02_xml_read_compositeValues();
		test03_xml_write_database();
	}
}
