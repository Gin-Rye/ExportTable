package com.export.data.input;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.export.exception.BussinessException;

public class SourceConfigurationFactory {
	public static SourceConfiguration getSourceConfiguration(
			String configurationFilePath) throws BussinessException {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(
					new File(configurationFilePath));
			Element root = document.getRootElement();
			String type = root.elementText("type");
			SourceType sourceType = SourceType.getSourceType(type);
			if(sourceType == null) {
				throw new BussinessException("Unsupported source type");
			}
			Element properties = root.element("properties");
			if(properties == null) {
				throw new BussinessException("Source properties format is wrong");
			}
			SourceConfiguration sourceConfiguration = generate(sourceType, properties);
			return sourceConfiguration;
		} catch (DocumentException e) {
			throw new BussinessException(e);
		}
	}
	
	private static SourceConfiguration generate(
			SourceType sourceType, Element properties) {
		SourceConfiguration sourceConfiguration = new SourceConfiguration();
		sourceConfiguration.setSourceType(sourceType);
		switch(sourceType) {
		case DB:
			Iterator iterator = properties.elementIterator();
			while(iterator.hasNext()) {
				Element element = (Element) iterator.next();
				String propertyName = element.getName();
				String propertyValue = element.getTextTrim();
				sourceConfiguration.getProperties().put(propertyName, propertyValue);
			}
			break;
		case HTTP:
			break;
		}
		return sourceConfiguration;
	}
}
