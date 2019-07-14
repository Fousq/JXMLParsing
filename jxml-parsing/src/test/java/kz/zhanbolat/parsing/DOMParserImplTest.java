package kz.zhanbolat.parsing;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import kz.zhanbolat.parsing.entity.Medicine;
import kz.zhanbolat.parsing.parser.DOMParserImpl;
import kz.zhanbolat.parsing.parser.XMLParser;

public class DOMParserImplTest {
	private static Logger logger = 
			LogManager.getLogger(DOMParserImplTest.class);
	private static DocumentBuilderFactory factory;
	private static DocumentBuilder builder;
	private static InputStream input;
	
	@BeforeClass
	public static void init() throws ParserConfigurationException {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		input = DOMParserImplTest.class.getClassLoader()
				.getResourceAsStream("medicins.xml");
	}
	
	@Test
	public void parseShouldWorkCorrectly() throws Exception {
		XMLParser parser = DOMParserImpl
				.newBuilder().setBuilder(builder).build();
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
