package kz.zhanbolat.parsing.parser;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import kz.zhanbolat.parsing.entity.Medicine;
import kz.zhanbolat.parsing.parser.handler.MedicineHandler;

public class SAXParserImpl implements XMLParser {
	private SAXParser parser;
	private SAXParserFactory factory;
	private DefaultHandler handler;
	
	private SAXParserImpl() {
		
	}
	
	@Override
	public List<Medicine> parse(InputStream input) throws SAXException,
														  IOException,
														  ParseException {
		parser.parse(input, handler);
		return ((MedicineHandler) handler).getMedicins();
	}
	
	public static Builder newBuilder() {
		return new SAXParserImpl().new Builder();
	}
	
	public class Builder {
		
		private Builder() {
			
		}
		
		public Builder setSAXParserFactory(SAXParserFactory factory) {
			SAXParserImpl.this.factory = factory;
			
			return this;
		}
		
		public Builder setHandler(DefaultHandler handler) {
			SAXParserImpl.this.handler = handler;
			
			return this;
		}
		
		public SAXParserImpl build() throws ParserConfigurationException, 
											SAXException {
			SAXParserImpl.this.parser = SAXParserImpl.this
					.factory.newSAXParser();
			return SAXParserImpl.this;
		}
		
	}
	
}
