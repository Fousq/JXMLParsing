package kz.zhanbolat.parsing;

import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.List;

import javax.xml.stream.XMLInputFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import kz.zhanbolat.parsing.entity.Medicine;
import kz.zhanbolat.parsing.parser.StAXParserImpl;
import kz.zhanbolat.parsing.parser.XMLParser;

public class StAXParserImplTest {
	private static Logger logger = 
			LogManager.getLogger(StAXParserImplTest.class);
	private static XMLInputFactory factory;
	private static InputStream input;
	
	@BeforeClass
	public static void init() {
		factory = XMLInputFactory.newInstance();
		input = StAXParserImplTest.class.getClassLoader()
				.getResourceAsStream("medicins.xml");
	}
	
	@Test
	public void parseShouldWorkCorrectly() throws Exception {
		XMLParser parser = StAXParserImpl
				.newBuilder().setFactory(factory).build();
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
