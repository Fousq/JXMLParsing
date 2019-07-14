package kz.zhanbolat.parsing.parser;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import kz.zhanbolat.parsing.entity.Pharm;

public class PharmParser {
	private static final short NAME_INDEX = 0;
	private static final short COUNTRY_INDEX = 1;
	
	public Pharm parse(Node node) {
		NamedNodeMap attributes = node.getAttributes();
		return (attributes.getLength() == 2) ? 
				(attributes.item(NAME_INDEX).getNodeName().equals("name")) ?
						new Pharm(attributes.item(NAME_INDEX).getNodeValue(),
								attributes.item(COUNTRY_INDEX).getNodeValue())
						: new Pharm(attributes
										.item(COUNTRY_INDEX).getNodeValue(),
								attributes.item(NAME_INDEX).getNodeValue())
				: new Pharm(attributes.item(NAME_INDEX).getNodeValue());
	}
	
}
