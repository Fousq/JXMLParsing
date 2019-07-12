package kz.zhanbolat.parsing.parser;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import kz.zhanbolat.parsing.entity.Medicine;
import kz.zhanbolat.parsing.entity.Pharm;
import kz.zhanbolat.parsing.entity.Version;

public class DOMParserImpl implements XMLParser {
	private static Logger logger = LogManager.getLogger(DOMParserImpl.class);
	private static final short NAME_INDEX = 1;
	private static final short PHARM_INDEX = 3;
	private static final short GROUP_INDEX = 5;
	private static final short ANALOGS_INDEX = 7;
	private static final short ANALOG_NAME_INDEX = 0;
	private static final short VERSIONS_INDEX = 9;
	private static PharmParser pharmParser = new PharmParser();
	private static VersionParser versionParser = new VersionParser();
	private DocumentBuilder builder;
	
	public DOMParserImpl(DocumentBuilder builder) {
		this.builder = builder;
	}
	
	public List<Medicine> parse(InputStream input) throws SAXException, 
													   IOException, 
													   ParseException {
		List<Medicine> medicines = new ArrayList<>();
		Document document = builder.parse(input);
		NodeList matches = document.getElementsByTagName("Medicine");
		for (int i = 0; i < matches.getLength(); i++) {
			Node node = matches.item(i);
			NodeList childs = node.getChildNodes();
			String name = childs.item(NAME_INDEX).getTextContent();
			node = childs.item(PHARM_INDEX);
			Pharm pharm = pharmParser.parse(node);
			String group = childs.item(GROUP_INDEX).getTextContent();
			List<String> analogs = new ArrayList<>();
			node = childs.item(ANALOGS_INDEX);
			NodeList analogsChilds = node.getChildNodes();
			for (int j = 1; j < analogsChilds.getLength(); j+=2) {
				NamedNodeMap analogAttr = analogsChilds.item(j).getAttributes();
				analogs.add(analogAttr.item(ANALOG_NAME_INDEX).getNodeValue());
			}
			List<Version> versions = new ArrayList<>();
			node = childs.item(VERSIONS_INDEX);
			NodeList versionsChilds = node.getChildNodes();
			for (int j = 1; j < versionsChilds.getLength(); j+=2) {
				versions.add(versionParser.parse(versionsChilds.item(j)));
			}
			medicines.add(new Medicine(name, pharm, group, analogs, versions));
		}
		return medicines;
	}
	
}
