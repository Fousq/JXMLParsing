package kz.zhanbolat.parsing;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import kz.zhanbolat.parsing.entity.Dosage;
import kz.zhanbolat.parsing.parser.DosageParser;

public class DosageParserTest {
	private static Logger logger = 
			LogManager.getLogger(DosageParserTest.class);
	private static DocumentBuilderFactory factory;
	private static DocumentBuilder builder;
	private static InputStream input;
	
	@BeforeClass
	public static void init() throws ParserConfigurationException {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		input = DosageParserTest.class.getClassLoader()
				.getResourceAsStream("medicins.xml");
	}
	
	@Test
	public void paserShouldWorkCorrectly() throws SAXException, IOException {
		Document document = builder.parse(input);
		NodeList dosages = document.getElementsByTagName("Dosage");
		Dosage dosage = new DosageParser().parse(dosages.item(0));
		logger.debug(dosage);
		assertTrue(dosage.getUnits().equals("ml"));
		assertTrue(dosage.getMeasuring() == 4);
		assertTrue(dosage.getFrequency().equals("2 times a day"));
	}
	
}
