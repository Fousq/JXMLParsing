package kz.zhanbolat.parsing.parser;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import kz.zhanbolat.parsing.entity.Medicine;
import kz.zhanbolat.parsing.parser.handler.MedicineHandler;

public class SAXParserImpl implements XMLParser {
	private SAXParser parser;
	private MedicineHandler handler;
	
	public SAXParserImpl(MedicineHandler handler) 
							throws ParserConfigurationException, SAXException {
		parser = SAXParserFactory.newInstance().newSAXParser();
		this.handler = handler;
	}
	
	@Override
	public List<Medicine> parse(InputStream input) throws SAXException, IOException, 
												  ParseException {
		parser.parse(input, handler);
		return handler.getMedicins();
	}
	
}
