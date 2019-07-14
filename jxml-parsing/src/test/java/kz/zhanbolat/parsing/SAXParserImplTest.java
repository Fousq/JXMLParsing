package kz.zhanbolat.parsing;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import kz.zhanbolat.parsing.entity.Medicine;
import kz.zhanbolat.parsing.parser.SAXParserImpl;
import kz.zhanbolat.parsing.parser.XMLParser;
import kz.zhanbolat.parsing.parser.handler.MedicineHandler;

public class SAXParserImplTest {
	private static Logger logger = 
			LogManager.getLogger(SAXParserImplTest.class);
	private static SAXParserFactory factory;
	private static DefaultHandler handler;
	private static InputStream input;
	
	@BeforeClass
	public static void init() throws ParserConfigurationException, 
									 SAXException {
		factory = SAXParserFactory.newInstance();
		handler = new MedicineHandler();
		input = SAXParserImplTest.class.getClassLoader()
				.getResourceAsStream("medicins.xml");
	}
	
	@Test
	public void parseShouldWorkCorrectly() throws Exception {
		XMLParser parser = SAXParserImpl.newBuilder()
				.setSAXParserFactory(factory).setHandler(handler).build();
		List<Medicine> medicins = parser.parse(input);
		assertTrue(medicins.size() != 0);
		Medicine medicine = medicins.get(0);
		logger.debug(medicine);
		assertTrue(medicine.getName().equals("Imodium"));
		assertTrue(medicine.getGroup().equals("Organotropic"));
		assertTrue(medicine.getAnalogs().get(0).equals("Loperamide"));
		assertTrue(medicine.getAnalogs().get(1).equals("Stoperan"));
	}
	
}
