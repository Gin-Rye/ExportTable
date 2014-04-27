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

public class SinkTypeMappingUtils {
	private static Map<String, String> outputServiceNameMap = new HashMap<String, String>();
	private static String sinkTypeMappingFilePath = "./res/sinkType.mapping.xml";
	
	static {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(
					new File(sinkTypeMappingFilePath));
			Element root = document.getRootElement();
			Iterator iterator = root.elementIterator();
			while(iterator.hasNext()) {
				Element mapping = (Element) iterator.next();
				String type = mapping.elementTextTrim("type").toUpperCase();
				String inputServiceName = mapping.elementTextTrim("outputServiceName");
				outputServiceNameMap.put(type, inputServiceName);
			}
		} catch(Exception e) {
			Logger.log(e);
		}
	}
	
	public static String getOutputServiceName(String type) throws BussinessException {
		type = type.toUpperCase();
		if(outputServiceNameMap.containsKey(type)) {
			return outputServiceNameMap.get(type);
		} else {
			throw new BussinessException("Not such source type");
		}
	}
}
