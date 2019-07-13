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
				if (reader.getLocalName().equals("Medicine")) {
					medicine = new Medicine();
				}
				if (reader.getLocalName().equals("Name")) {
					medicine.setName(reader.getElementText());
				}
				if (reader.getLocalName().equals("Pharm")) {
					pharm = (reader.getAttributeCount() == 2)? 
								new Pharm(reader.getAttributeValue(null, "name"),
										  reader.getAttributeValue(null, "country"))
								: new Pharm(reader.getAttributeValue(null, "name"));
					medicine.setPharm(pharm);
				}
				if (reader.getLocalName().equals("Group")) {
					medicine.setGroup(reader.getElementText());
				}
				if (reader.getLocalName().equals("Analogs")) {
					analogs = new ArrayList<>();
				}
				if (reader.getLocalName().equals("Analog")) {
					analogs.add(reader.getAttributeValue(null, "name"));
				}
				if (reader.getLocalName().equals("Versions")) {
					versions = new ArrayList<>();
				}
				if (reader.getLocalName().equals("Version")) {
					version = new Version();
					version.setConsistency(reader.getAttributeValue(null, "consistency"));
				}
				if (reader.getLocalName().equals("Certificate")) {
					certificate = new Certificate();
					int id = Integer.parseInt(reader.getAttributeValue(null, "id").substring(3));
					certificate.setId(id);
				}
				if (reader.getLocalName().equals("Date_of_issue")) {
					try {
						certificate.setDateOfIssue(reader.getElementText());
					} catch (ParseException e) {
						logger.error("Error in the format of the date. Value will be "
								+ "setted to today's date.", e);
						certificate.setDateOfIssue(new Date());
					}
				}
				if (reader.getLocalName().equals("Expiration_date")) {
					try {
						certificate.setExparetionDate(reader.getElementText());
					} catch (ParseException e) {
						logger.error("Error in the format of the date. Value will be "
								+ "setted to today's date.", e);
						certificate.setExparetionDate(new Date());
					}
				}
				if (reader.getLocalName().equals("Registering_organization")) {
					certificate.setRegisterOrg(reader.getAttributeValue(null, "name"));
				}
				if (reader.getLocalName().equals("Package")) {
					medPack = new MedicinePackage();
					medPack.setType(reader.getAttributeValue(null, "type"));
				}
				if (reader.getLocalName().equals("Quantity_in_package")) {
					int quantity = Integer.parseInt(reader.getElementText());
					medPack.setQuantity(quantity);
				}
				if (reader.getLocalName().equals("Price")) {
					int price = Integer.parseInt(reader.getElementText());
					medPack.setPrice(price);
				}
				if (reader.getLocalName().equals("Dosage")) {
					dosage = new Dosage();
					dosage.setUnits(reader.getAttributeValue(null, "units"));
				}
				if (reader.getLocalName().equals("Measuring")) {
					int measuring = Integer.parseInt(reader.getElementText());
					dosage.setMeasuring(measuring);
				}
				if (reader.getLocalName().equals("Frequency")) {
					dosage.setFrequency(reader.getElementText());
				}
			} else if (reader.getEventType() == XMLStreamReader.END_ELEMENT) {
				if (reader.getLocalName().equals("Medicine")) {
					medicins.add(medicine);
				}
				if (reader.getLocalName().equals("Analogs")) {
					medicine.setAnalogs(analogs);
				}
				if (reader.getLocalName().equals("Versions")) {
					medicine.setVersions(versions);
				}
				if (reader.getLocalName().equals("Version")) {
					versions.add(version);
				}
				if (reader.getLocalName().equals("Certificate")) {
					version.setCertificate(certificate);
				}
				if (reader.getLocalName().equals("Package")) {
					version.setPack(medPack);
				}
				if (reader.getLocalName().equals("Dosage")) {
					version.setDosage(dosage);
				}
			}
		}
		return medicins;
	}

}
