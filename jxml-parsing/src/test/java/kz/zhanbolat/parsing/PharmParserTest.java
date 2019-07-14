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
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import kz.zhanbolat.parsing.entity.Pharm;
import kz.zhanbolat.parsing.parser.PharmParser;

public class PharmParserTest {
	private static Logger logger = 
			LogManager.getLogger(PharmParserTest.class);
	private static DocumentBuilderFactory factory;
	private static DocumentBuilder builder;
	private static InputStream input;
	
	@BeforeClass
	public static void init() throws ParserConfigurationException {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		input = PharmParserTest.class.getClassLoader()
				.getResourceAsStream("medicins.xml");
	}
	
	@Test
	public void parseShouldWorkCorrectly() throws SAXException, IOException {
		Document document = builder.parse(input);
		Node node = document.getElementsByTagName("Pharm").item(0);
		logger.debug(document.getElementsByTagName("Pharm").getLength());
		logger.debug(node.getNodeValue());
		Pharm pharm = new PharmParser().parse(node);
		logger.debug(pharm);
		assertTrue(pharm.getName().equals("Janssen-cilag S.A."));
		assertTrue(pharm.getCountry().equals("France"));
	}
	
}
