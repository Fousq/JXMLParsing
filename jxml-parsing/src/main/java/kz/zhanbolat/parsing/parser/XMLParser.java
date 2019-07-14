package kz.zhanbolat.parsing.parser;

import java.io.InputStream;
import java.util.List;

import kz.zhanbolat.parsing.entity.Medicine;

public interface XMLParser {
	List<Medicine> parse(InputStream input) throws Exception;
}
