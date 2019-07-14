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

import kz.zhanbolat.parsing.entity.MedicinePackage;
import kz.zhanbolat.parsing.parser.MedicinePackageParser;

public class MedicinePackageParserTest {
	private static Logger logger = 
			LogManager.getLogger(MedicinePackageParserTest.class);
	private static DocumentBuilderFactory factory;
	private static DocumentBuilder builder;
	private static InputStream input;
	
	@BeforeClass
	public static void init() throws ParserConfigurationException {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		input = MedicinePackageParserTest.class.getClassLoader()
				.getResourceAsStream("medicins.xml");
	}
	
	@Test
	public void parseShouldWorkCorrectly() throws SAXException, IOException {
		Document document = builder.parse(input);
		NodeList packages = document.getElementsByTagName("Package");
		MedicinePackage medPackage = 
				new MedicinePackageParser().parse(packages.item(0));
		logger.debug(medPackage);
		assertTrue(medPackage.getType().equals("Tutu"));
		assertTrue(medPackage.getQuantity() == 20);
		assertTrue(medPackage.getPrice() == 2345);
	}
	
}
