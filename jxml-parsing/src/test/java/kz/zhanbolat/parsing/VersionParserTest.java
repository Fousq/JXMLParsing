package kz.zhanbolat.parsing;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;

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

import kz.zhanbolat.parsing.entity.Version;
import kz.zhanbolat.parsing.parser.CertificateParser;
import kz.zhanbolat.parsing.parser.DosageParser;
import kz.zhanbolat.parsing.parser.MedicinePackageParser;
import kz.zhanbolat.parsing.parser.VersionParser;

public class VersionParserTest {
	private static Logger logger = LogManager.getLogger(VersionParserTest.class);
	private static DocumentBuilderFactory factory;
	private static DocumentBuilder builder;
	
	@BeforeClass
	public static void init() throws ParserConfigurationException {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
	}
	
	@Test
	public void parseShouldWorkCorrectly() throws SAXException, IOException, ParseException {
		Document document = builder.parse(getClass().getClassLoader().getResourceAsStream("medicins.xml"));
		NodeList versions = document.getElementsByTagName("Version");
		Version version = new VersionParser().parse(versions.item(0));
		logger.debug(version);
		assertTrue(version.getConsistency().equals("Pills"));
		assertTrue(version.getCertificate()
							.equals(new CertificateParser()
									.parse(document
										.getElementsByTagName("Certificate")
											.item(0))));
		assertTrue(version.getPack()
							.equals(new MedicinePackageParser()
									.parse(document
										.getElementsByTagName("Package")
											.item(0))));
		assertTrue(version.getDosage()
							.equals(new DosageParser()
								.parse(document
									.getElementsByTagName("Dosage")
										.item(0))));
	}
	
}
