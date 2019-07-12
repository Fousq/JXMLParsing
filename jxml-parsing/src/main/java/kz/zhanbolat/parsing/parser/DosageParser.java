package kz.zhanbolat.parsing.parser;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kz.zhanbolat.parsing.entity.Dosage;

public class DosageParser {
	private static final short UNITS_INDEX = 0;
	private static final short MEASURING_INDEX = 1;
	private static final short FREQUENCY_INDEX = 3;
	
	public Dosage parse(Node node) {
		String units = node.getAttributes().item(UNITS_INDEX).getNodeValue();
		NodeList childs = node.getChildNodes();
		int measuring = Integer.parseInt(childs.item(MEASURING_INDEX).getTextContent());
		String frequency = childs.item(FREQUENCY_INDEX).getTextContent();
		return new Dosage(units, measuring, frequency);
	}
	
}
