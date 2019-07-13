package kz.zhanbolat.parsing.parser;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import org.xml.sax.SAXException;

import kz.zhanbolat.parsing.entity.Medicine;

public interface XMLParser {
	List<Medicine> parse(InputStream input) throws Exception;
}
