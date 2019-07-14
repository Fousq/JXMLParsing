package kz.zhanbolat.parsing.parser.handler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import kz.zhanbolat.parsing.entity.Certificate;
import kz.zhanbolat.parsing.entity.Dosage;
import kz.zhanbolat.parsing.entity.Medicine;
import kz.zhanbolat.parsing.entity.MedicinePackage;
import kz.zhanbolat.parsing.entity.Pharm;
import kz.zhanbolat.parsing.entity.Version;

public class MedicineHandler extends DefaultHandler {
	private static Logger logger = LogManager.getLogger(MedicineHandler.class);
	private List<Medicine> medicins = new ArrayList<>();
	private Medicine medicine;
	private Pharm pharm;
	private List<String> analogs;
	private List<Version> versions;
	private Version version;
	private Certificate certificate;
	private MedicinePackage medPack;
	private Dosage dosage;
	
	private boolean bmn = false;
	private boolean bmg = false;
	private boolean bdoi = false;
	private boolean bexrd = false;
	private boolean bqip = false;
	private boolean bpp = false;
	private boolean bdm = false;
	private boolean bdf = false;
	
	@Override
	public void startElement(String uri, String localName, String qName,
								Attributes attributes) throws SAXException {
		switch(qName) {
		case "Medicine":
			medicine = new Medicine();
			break;
		case "Name":
			bmn = true;
			break;
		case "Pharm":
			pharm = new Pharm(attributes.getValue("name"), 
							  attributes.getValue("country"));
			medicine.setPharm(pharm);
			break;
		case "Group":
			bmg = true;
			break;
		case "Analogs":
			analogs = new ArrayList<>();
			break;
		case "Analog":
			analogs.add(attributes.getValue("name"));
			break;
		case "Versions":
			versions = new ArrayList<>();
			break;
		case "Version":
			version = new Version();
			String consistency = attributes.getValue("consistency");
			version.setConsistency(consistency);
			break;
		case "Certificate":
			certificate = new Certificate();
			int id = Integer.parseInt(attributes.getValue("id").substring(3));
			certificate.setId(id);
			break;
		case "Date_of_issue":
			bdoi = true;
			break;
		case "Expiration_date":
			bexrd = true;
			break;
		case "Registering_organization":
			certificate.setRegisterOrg(attributes.getValue("name"));
			break;
		case "Package":
			medPack = new MedicinePackage();
			String type = attributes.getValue("type");
			medPack.setType(type);
			break;
		case "Quantity_in_package":
			bqip = true;
			break;
		case "Price":
			bpp = true;
			break;
		case "Dosage":
			dosage = new Dosage();
			String units = attributes.getValue("units");
			dosage.setUnits(units);
			break;
		case "Measuring":
			bdm = true;
			break;
		case "Frequency":
			bdf = true;
			break;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) 
												throws SAXException {
		if (bmn) {
			medicine.setName(new String(ch, start, length));
			bmn = false;
		} else if (bmg) {
			medicine.setGroup(new String(ch, start, length));
			bmg = false;
		} else if (bdoi) {
			try {
				certificate.setDateOfIssue(new String(ch, start, length));
			} catch (ParseException e) {
				logger.error("Error in the format of the date. Value will be "
							+ "setted to today's date.", e);
				certificate.setDateOfIssue(new Date());
			} finally {
				bdoi = false;
			}
		} else if (bexrd) {
			try {
				certificate.setExparetionDate(new String(ch, start, length));
			} catch (ParseException e) {
				logger.error("Error in the format of the date. Value will be "
							+ "setted to today's date.", e);
				certificate.setExparetionDate(new Date());
			} finally {
				bexrd = false;
			}
		} else if (bqip) {
			int quantity = Integer.parseInt(new String(ch, start, length));
			medPack.setQuantity(quantity);
			bqip = false;
		} else if (bpp) {
			int price = Integer.parseInt(new String(ch, start, length));
			medPack.setPrice(price);
			bpp = false;
		} else if (bdm) {
			int measuring = Integer.parseInt(new String(ch, start, length));
			dosage.setMeasuring(measuring);
			bdm = false;
		} else if (bdf) {
			dosage.setFrequency(new String(ch, start, length));
			bdf = false;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) 
														throws SAXException {
		switch(qName) {
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
	
	public List<Medicine> getMedicins() {
		return medicins;
	}
	
}
