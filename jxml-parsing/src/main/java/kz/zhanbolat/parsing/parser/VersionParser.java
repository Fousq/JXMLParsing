package kz.zhanbolat.parsing.parser;

import java.text.ParseException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kz.zhanbolat.parsing.entity.Certificate;
import kz.zhanbolat.parsing.entity.Dosage;
import kz.zhanbolat.parsing.entity.MedicinePackage;
import kz.zhanbolat.parsing.entity.Version;

public class VersionParser {
	private static final short CONSISTENCY_INDEX = 0;
	private static final short CERTIFICATE_INDEX = 1;
	private static final short PACKAGE_INDEX = 3;
	private static final short DOSAGE_INDEX = 5;
	
	public Version parse(Node node) throws ParseException {
		String consistency = node.getAttributes().item(CONSISTENCY_INDEX).getNodeValue();
		NodeList childs = node.getChildNodes();
		Certificate certificate = new CertificateParser().parse(childs.item(CERTIFICATE_INDEX));
		MedicinePackage medPackage = new MedicinePackageParser().parse(childs.item(PACKAGE_INDEX));
		Dosage dosage = new DosageParser().parse(childs.item(DOSAGE_INDEX));
		return new Version(consistency, certificate, medPackage, dosage);
	}
	
}
