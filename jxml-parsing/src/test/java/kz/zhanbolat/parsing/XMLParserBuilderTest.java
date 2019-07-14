package kz.zhanbolat.parsing;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import kz.zhanbolat.parsing.entity.Medicine;
import kz.zhanbolat.parsing.parser.XMLParser;
import kz.zhanbolat.parsing.parser.XMLParserBuilder;
import kz.zhanbolat.parsing.parser.handler.MedicineHandler;

public class XMLParserBuilderTest {
	private static Logger logger = LogManager.getLogger(XMLParserBuilder.class);
	private static XMLParserBuilder builder;
	
	@BeforeClass
	public static void init() {
		builder = new XMLParserBuilder();
	}
	
	@Test
	public void DOMParserShouldWorkCorrectly() throws Exception {
		logger.debug("DOMParser");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		XMLParser parser = builder.setDocBuilder(factory.newDocumentBuilder()).build();
		List<Medicine> medicins = parser.parse(getClass().getClassLoader().getResourceAsStream("medicins.xml"));
		medicins.forEach(medicine -> logger.debug(medicine));
		assertTrue(medicins.size() == 16);
	}
	
	@Test
	public void SAXParserShouldWorkCorrectly() throws Exception {
		logger.debug("SAXParser");
		XMLParser parser = builder.setHanlder(new MedicineHandler()).build();
		List<Medicine> medicins = parser.parse(getClass().getClassLoader().getResourceAsStream("medicins.xml"));
		medicins.forEach(medicine -> logger.debug(medicine));
		assertTrue(medicins.size() == 16);
	}
	
	@Test
	public void StAXParserShouldWorkCorrectly() throws Exception {
		logger.debug("StAXParser");
		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLParser parser = builder.setInputFactory(factory).build();
		List<Medicine> medicins = parser.parse(getClass().getClassLoader().getResourceAsStream("medicins.xml"));
		medicins.forEach(medicine -> logger.debug(medicine));
		assertTrue(medicins.size() == 16);
	}
	
}
