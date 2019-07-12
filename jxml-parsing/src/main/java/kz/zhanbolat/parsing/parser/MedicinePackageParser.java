package kz.zhanbolat.parsing.parser;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kz.zhanbolat.parsing.entity.MedicinePackage;

public class MedicinePackageParser {
	private static final short TYPE_INDEX = 0;
	private static final short QUANTITY_INDEX = 1;
	private static final short PRICE_INDEX = 3;
	
	public MedicinePackage parse(Node node) {
		String type = node.getAttributes().item(TYPE_INDEX).getNodeValue();
		NodeList childs = node.getChildNodes();
		int quantity = Integer.parseInt(childs.item(QUANTITY_INDEX).getTextContent());
		int price = Integer.parseInt(childs.item(PRICE_INDEX).getTextContent());
		return new MedicinePackage(type, quantity, price);
	}
	
}
