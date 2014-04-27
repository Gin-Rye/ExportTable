package com.export.base.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.export.base.exception.BussinessException;
import com.export.base.log.Logger;

public class SourceTypeMappingUtils {
	private static Map<String, String> inputServiceNameMap = new HashMap<String, String>();
	private static String sourceTypeMappingFilePath = "./res/sourceType.mapping.xml";
	
	static {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(
					new File(sourceTypeMappingFilePath));
			Element root = document.getRootElement();
			Iterator iterator = root.elementIterator();
			while(iterator.hasNext()) {
				Element mapping = (Element) iterator.next();
				String type = mapping.elementTextTrim("type").toUpperCase();
				String inputServiceName = mapping.elementTextTrim("inputServiceName");
				inputServiceNameMap.put(type, inputServiceName);
			}
		} catch(Exception e) {
			Logger.log(e);
		}
	}
	
	public static String getInputServiceName(String type) throws BussinessException {
		type = type.toUpperCase();
		if(inputServiceNameMap.containsKey(type)) {
			return inputServiceNameMap.get(type);
		} else {
			throw new BussinessException("Not such source type");
		}
	}
}
