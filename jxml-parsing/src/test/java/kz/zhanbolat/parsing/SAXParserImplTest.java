package kz.zhanbolat.parsing;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import kz.zhanbolat.parsing.entity.Medicine;
import kz.zhanbolat.parsing.parser.SAXParserImpl;
import kz.zhanbolat.parsing.parser.handler.MedicineHandler;

public class SAXParserImplTest {
	private static Logger logger = LogManager.getLogger(SAXParserImplTest.class);
	private static MedicineHandler handler;
	private static SAXParserImpl parser;
	
	@BeforeClass
	public static void init() throws ParserConfigurationException, SAXException {
		handler = new MedicineHandler();
		parser = new SAXParserImpl(handler);
	}
	
	@Test
	public void parseShouldWorkCorrectly() throws SAXException, 
												  IOException, 
												  ParseException {
		List<Medicine> medicins = parser.parse(getClass()
												.getClassLoader()
												  .getResourceAsStream("medicins.xml"));
		assertTrue(medicins.size() != 0);
		Medicine medicine = medicins.get(0);
		logger.debug(medicine);
		assertTrue(medicine.getName().equals("Imodium"));
		assertTrue(medicine.getGroup().equals("Organotropic"));
		assertTrue(medicine.getAnalogs().get(0).equals("Loperamide"));
		assertTrue(medicine.getAnalogs().get(1).equals("Stoperan"));
	}
	
}
