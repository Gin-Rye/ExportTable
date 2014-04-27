package com.export.control.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.export.base.exception.BussinessException;
import com.export.model.configuration.Configuration;

public class SourceConfigurationFactory {
	public static List<Configuration> getSourceConfiguration(
			String sourceConfigurationFilePath) throws BussinessException {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(
					new File(sourceConfigurationFilePath));
			Element root = document.getRootElement();
			Iterator iterator = root.elementIterator();
			List<Configuration> sourceConfigurationList = 
				new ArrayList<Configuration>();
			while(iterator.hasNext()) {
				Configuration sourceConfiguration = 
					new Configuration();
				Element source = (Element) iterator.next();
				String type = source.elementTextTrim("type").toUpperCase();
				sourceConfiguration.setType(type);
				Element properties = source.element("properties");
				Iterator propertiesIterator = properties.elementIterator();
				while(propertiesIterator.hasNext()) {
					Element property = (Element) propertiesIterator.next();
					String key = property.getName().trim();
					String value = property.getTextTrim();
					sourceConfiguration.getProperties().put(key, value);
				}
				sourceConfigurationList.add(sourceConfiguration);
			}
			return sourceConfigurationList;
		} catch (DocumentException e) {
			throw new BussinessException(e);
		}
	}
}
