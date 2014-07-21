package com.export.base.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.export.base.exception.BusinessException;
import com.export.base.log.Logger;

public class SourceMappingUtils {
	private static Map<String, String> inputServiceNameMap = new HashMap<String, String>();
	private static Map<String, String> sourceConfigurationMap = new HashMap<String, String>();
	private static String sourceTypeMappingFilePath = "./res/source.mapping.xml";
	
	static {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(
					new File(sourceTypeMappingFilePath));
			Element root = document.getRootElement();
			Iterator<?> iterator = root.elementIterator();
			while(iterator.hasNext()) {
				Element mapping = (Element) iterator.next();
				String type = mapping.elementTextTrim("type").toUpperCase();
				Element inputService = mapping.element("inputService");
				String inputServiceName = inputService.elementTextTrim("name");
				inputServiceNameMap.put(type, inputServiceName);
				Element sourceConfiguration = mapping.element("sourceConfiguration");
				String sourceConfigurationName = sourceConfiguration.elementText("name");
				sourceConfigurationMap.put(type, sourceConfigurationName);
			}
		} catch(Exception e) {
			Logger.log(e);
		}
	}
	
	public static String getInputServiceName(String type) throws BusinessException {
		type = type.toUpperCase();
		if(inputServiceNameMap.containsKey(type)) {
			return inputServiceNameMap.get(type);
		} else {
			throw new BusinessException("Not such source type of inputService");
		}
	}
	
	public static String getSourceConfigurationName(String type) throws BusinessException {
		type = type.toUpperCase();
		if(sourceConfigurationMap.containsKey(type)) {
			return sourceConfigurationMap.get(type);
		} else {
			throw new BusinessException("Not such source type of sourceConfiguration");
		}
	}
}
