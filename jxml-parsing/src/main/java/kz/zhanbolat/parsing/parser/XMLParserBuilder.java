package kz.zhanbolat.parsing.parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;

import org.xml.sax.SAXException;

import kz.zhanbolat.parsing.parser.handler.MedicineHandler;

public class XMLParserBuilder {
	private XMLParser parser;
	private MedicineHandler handler;
	private DocumentBuilder docBuilder;
	private XMLInputFactory inputFactory;
	private boolean isBuildable = false;
	
	public XMLParserBuilder setInputFactory(XMLInputFactory inputFactory) {
		if (!isBuildable) {
			this.inputFactory = inputFactory;
			isBuildable = true;
		}
		return this;
	}
	
	public XMLParserBuilder setDocBuilder(DocumentBuilder docBuilder) {
		if (!isBuildable) {
			this.docBuilder = docBuilder;
			isBuildable = true;
		}
		return this;
	}
	
	public XMLParserBuilder setHanlder(MedicineHandler handler) {
		if (!isBuildable) {
			this.handler = handler;
			isBuildable = true;
		}
		return this;
	}
	
	public XMLParser build() throws ParserConfigurationException, SAXException {
		if (handler != null) {
			parser = new SAXParserImpl(handler);
		} else if (docBuilder != null) {
			parser = new DOMParserImpl(docBuilder);
		} else if (inputFactory != null) {
			parser = new StAXParserImpl();
		}
		return parser;
	}
	
}
