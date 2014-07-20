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

public class SinkMappingUtils {
	private static Map<String, String> outputServiceNameMap = new HashMap<String, String>();
	private static Map<String, String> sinkConfigurationMap = new HashMap<String, String>();
	private static String sinkTypeMappingFilePath = "./res/sink.mapping.xml";
	
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
				Element outputService = mapping.element("outputService");
				String outputServiceName = outputService.elementTextTrim("name");
				outputServiceNameMap.put(type, outputServiceName);
				Element sinkConfiguration = mapping.element("sinkConfiguration");
				String sinkConfigurationName = sinkConfiguration.elementText("name");
				sinkConfigurationMap.put(type, sinkConfigurationName);
			}
		} catch(Exception e) {
			Logger.log(e);
		}
	}
	
	public static String getOutputServiceName(String type) throws BusinessException {
		type = type.toUpperCase();
		if(outputServiceNameMap.containsKey(type)) {
			return outputServiceNameMap.get(type);
		} else {
			throw new BusinessException("Not such sink type of outputService");
		}
	}
	
	public static String getSinkConfigurationName(String type) throws BusinessException {
		type = type.toUpperCase();
		if(sinkConfigurationMap.containsKey(type)) {
			return sinkConfigurationMap.get(type);
		} else {
			throw new BusinessException("Not such sink type of sinkConfiguration");
		}
	}
}
