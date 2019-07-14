package kz.zhanbolat.parsing;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

import kz.zhanbolat.parsing.entity.Certificate;
import kz.zhanbolat.parsing.parser.CertificateParser;

public class CertificateParserTest {
	private static Logger logger = 
			LogManager.getLogger(CertificateParserTest.class);
	private static DocumentBuilderFactory factory;
	private static DocumentBuilder builder;
	private static SimpleDateFormat format = 
			new SimpleDateFormat(Certificate.DATE_FORMAT);
	private static InputStream input;
	
	@BeforeClass
	public static void init() throws ParserConfigurationException {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		input = CertificateParserTest.class.getClassLoader()
				.getResourceAsStream("medicins.xml");
	}
	
	@Test
	public void parseShouldWorkCorrectly() throws SAXException, 
												  IOException, 
												  ParseException {
		Document document = builder.parse(input);
		NodeList certificates = document.getElementsByTagName("Certificate");
		Certificate certificate = 
				new CertificateParser().parse(certificates.item(0));
		logger.debug(certificate);
		assertTrue(certificate.getDateOfIssue()
				.compareTo(format.parse("07-2016")) == 0);
		assertTrue(certificate.getExparetionDate()
				.compareTo(format.parse("06-2021")) == 0);
		assertTrue(certificate.getId() == 1);
		assertTrue(certificate.getRegisterOrg().equals("Jonson&Jonson"));
	}
	
}
