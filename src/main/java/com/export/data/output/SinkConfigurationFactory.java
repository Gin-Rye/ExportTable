package com.export.data.output;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.export.exception.BussinessException;

public class SinkConfigurationFactory {
	public static SinkConfiguration getSinkConfiguration(
			String configurationFilePath) throws BussinessException {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(
					new File(configurationFilePath));
			Element root = document.getRootElement();
			String type = root.elementText("type");
			SinkType sinkType = SinkType.getSinkType(type);
			if(sinkType == null) {
				throw new BussinessException("Unsupported sink type");
			}
			Element properties = root.element("properties");
			if(properties == null) {
				throw new BussinessException("Sink properties format is wrong");
			}
			SinkConfiguration sinkConfiguration = generate(sinkType, properties);
			return sinkConfiguration;
		} catch(DocumentException e) {
			throw new BussinessException(e);
		}
	}
	
	private static SinkConfiguration generate(
			SinkType sinkType, Element properties) {
		SinkConfiguration sinkConfiguration = new SinkConfiguration();
		sinkConfiguration.setSinkType(sinkType);
		switch(sinkType) {
		case FILE:
			Iterator iterator = properties.elementIterator();
			while(iterator.hasNext()) {
				Element element = (Element) iterator.next();
				String propertyName = element.getName();
				String propertyValue = element.getTextTrim();
				sinkConfiguration.getProperties().put(propertyName, propertyValue);
			}
			break;
		}
		return sinkConfiguration;
	}
}
