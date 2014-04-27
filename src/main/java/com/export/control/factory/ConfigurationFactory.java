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

public class ConfigurationFactory {
	public static List<Configuration> getConfiguration(
			String configurationFilePath) throws BussinessException {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(
					new File(configurationFilePath));
			Element root = document.getRootElement();
			Iterator iterator = root.elementIterator();
			List<Configuration> configurationList = 
				new ArrayList<Configuration>();
			while(iterator.hasNext()) {
				Configuration configuration = 
					new Configuration();
				Element element = (Element) iterator.next();
				String type = element.elementTextTrim("type").toUpperCase();
				configuration.setType(type);
				Element properties = element.element("properties");
				Iterator propertiesIterator = properties.elementIterator();
				while(propertiesIterator.hasNext()) {
					Element property = (Element) propertiesIterator.next();
					String key = property.getName().trim();
					String value = property.getTextTrim();
					configuration.getProperties().put(key, value);
				}
				configurationList.add(configuration);
			}
			return configurationList;
		} catch (DocumentException e) {
			throw new BussinessException(e);
		}
	}
}
