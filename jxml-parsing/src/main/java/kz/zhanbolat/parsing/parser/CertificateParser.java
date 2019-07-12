package kz.zhanbolat.parsing.parser;

import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kz.zhanbolat.parsing.entity.Certificate;

public class CertificateParser {
	private static Logger logger = LogManager.getLogger(CertificateParser.class);
	private static final short ID_INDEX = 0;
	private static final short DATE_OF_ISSUE_INDEX = 1;
	private static final short EXPARETION_DATE_INDEX = 3;
	private static final short REGISTR_ORG_INDEX = 5;
	private static final short REGISTR_ORG_NAME_INDEX = 0;
	
	public Certificate parse(Node node) throws ParseException {
		int id = Integer.parseInt(node.getAttributes().item(ID_INDEX).getNodeValue().substring(3));
		NodeList childs = node.getChildNodes();
		String dateOfIssue = childs.item(DATE_OF_ISSUE_INDEX).getTextContent();
		String exparetionDate = childs.item(EXPARETION_DATE_INDEX).getTextContent();
		String registrOrg = childs.item(REGISTR_ORG_INDEX).getAttributes().item(REGISTR_ORG_NAME_INDEX).getNodeValue();
		return new Certificate(id, dateOfIssue, exparetionDate, registrOrg);
	}
	
}
