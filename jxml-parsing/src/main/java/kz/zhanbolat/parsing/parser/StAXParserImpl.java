package kz.zhanbolat.parsing.parser;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import kz.zhanbolat.parsing.entity.Certificate;
import kz.zhanbolat.parsing.entity.Dosage;
import kz.zhanbolat.parsing.entity.Medicine;
import kz.zhanbolat.parsing.entity.MedicinePackage;
import kz.zhanbolat.parsing.entity.Pharm;
import kz.zhanbolat.parsing.entity.Version;

public class StAXParserImpl implements XMLParser {
	private static Logger logger = LogManager.getLogger(StAXParserImpl.class);
	private XMLInputFactory factory;
	
	public StAXParserImpl(XMLInputFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public List<Medicine> parse(InputStream input) throws SAXException, 
														  IOException,
														  XMLStreamException {
		XMLStreamReader reader = factory.createXMLStreamReader(input);
		List<Medicine> medicins = new ArrayList<>();
		Medicine medicine = null;
		Pharm pharm;
		Version version = null;
		Certificate certificate = null;
		MedicinePackage medPack = null;
		Dosage dosage = null;
		List<String> analogs = null;
		List<Version> versions = null;
		while(reader.hasNext()) {
			reader.next();
			if (reader.getEventType() == XMLStreamReader.START_ELEMENT) {
				switch(reader.getLocalName()) {
				case "Medicine":
					medicine = new Medicine();
					break;
				case "Name":
					medicine.setName(reader.getElementText());
					break;
				case "Pharm":
					pharm = new Pharm(reader.getAttributeValue(null, "name"),
									  reader.getAttributeValue(null, "country"));
					medicine.setPharm(pharm);
					break;
				case "Group":
					medicine.setGroup(reader.getElementText());
					break;
				case "Analogs":
					analogs = new ArrayList<>();
					break;
				case "Analog":
					analogs.add(reader.getAttributeValue(null, "name"));
					break;
				case "Versions":
					versions = new ArrayList<>();
					break;
				case "Version":
					version = new Version();
					version.setConsistency(reader.getAttributeValue(null, "consistency"));
					break;
				case "Certificate":
					certificate = new Certificate();
					int id = Integer.parseInt(reader.getAttributeValue(null, "id").substring(3));
					certificate.setId(id);
					break;
				case "Date_of_issue":
					try {
						certificate.setDateOfIssue(reader.getElementText());
					} catch (ParseException e) {
						logger.error("Error in the format of the date. Value will be "
								+ "setted to today's date.", e);
						certificate.setDateOfIssue(new Date());
					}
					break;
				case "Expiration_date":
					try {
						certificate.setExparetionDate(reader.getElementText());
					} catch (ParseException e) {
						logger.error("Error in the format of the date. Value will be "
								+ "setted to today's date.", e);
						certificate.setExparetionDate(new Date());
					}
					break;
				case "Registering_organization":
					certificate.setRegisterOrg(reader.getAttributeValue(null, "name"));
					break;
				case "Package":
					medPack = new MedicinePackage();
					medPack.setType(reader.getAttributeValue(null, "type"));
					break;
				case "Quantity_in_package":
					int quantity = Integer.parseInt(reader.getElementText());
					medPack.setQuantity(quantity);
					break;
				case "Price":
					int price = Integer.parseInt(reader.getElementText());
					medPack.setPrice(price);
					break;
				case "Dosage":
					dosage = new Dosage();
					dosage.setUnits(reader.getAttributeValue(null, "units"));
					break;
				case "Measuring":
					int measuring = Integer.parseInt(reader.getElementText());
					dosage.setMeasuring(measuring);
					break;
				case "Frequency":
					dosage.setFrequency(reader.getElementText());
					break;
				}
			} else if (reader.getEventType() == XMLStreamReader.END_ELEMENT) {
				switch(reader.getLocalName()) {
				case "Medicine":
					medicins.add(medicine);
					break;
				case "Analogs":
					medicine.setAnalogs(analogs);
					break;
				case "Versions":
					medicine.setVersions(versions);
					break;
				case "Version":
					versions.add(version);
					break;
				case "Certificate":
					version.setCertificate(certificate);
					break;
				case "Package":
					version.setPack(medPack);
					break;
				case "Dosage":
					version.setDosage(dosage);
					break;
				}
			}
		}
		return medicins;
	}

}
