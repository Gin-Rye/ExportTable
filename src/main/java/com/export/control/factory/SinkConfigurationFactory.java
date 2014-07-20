package com.export.control.factory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.export.base.exception.BusinessException;
import com.export.base.utils.SinkMappingUtils;
import com.export.model.configuration.SinkConfiguration;

public class SinkConfigurationFactory {
	public static List<SinkConfiguration> getSinkConfiguration(
			String sinkConfigurationFilePath) throws BusinessException {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(
					new File(sinkConfigurationFilePath));
			Element root = document.getRootElement();
			Iterator<?> iterator = root.elementIterator();
			List<SinkConfiguration> sinkConfigurationList = 
				new ArrayList<SinkConfiguration>();
			while(iterator.hasNext()) {
				Element element = (Element) iterator.next();
				String type = element.elementTextTrim("type").toUpperCase();
				String sinkConfigurationName = SinkMappingUtils.getSinkConfigurationName(type);
				Class<?> sinkConfigurationClass = Class.forName(sinkConfigurationName);
				Constructor<?> sinkConfigurationConstructor = 
					sinkConfigurationClass.getConstructor(String.class);
				SinkConfiguration sinkConfiguration = 
					(SinkConfiguration) sinkConfigurationConstructor.newInstance(type);
				Element properties = element.element("properties");
				Iterator<?> propertyIterator = properties.elementIterator();
				while(propertyIterator.hasNext()) {
					Element property = (Element) propertyIterator.next();
					String propertyName = property.elementText("name");
					String propertyValue = property.elementText("value");
					sinkConfiguration.setProperty(propertyName, propertyValue);
				}
				sinkConfigurationList.add(sinkConfiguration);
			}
			return sinkConfigurationList;
		} catch(ClassNotFoundException e) {
			throw new BusinessException(e);
		} catch (DocumentException e) {
			throw new BusinessException(e);
		} catch (InstantiationException e) {
			throw new BusinessException(e);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (NoSuchMethodException e) {
			throw new BusinessException(e);
		} catch (SecurityException e) {
			throw new BusinessException(e);
		} catch (IllegalArgumentException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}
	}
}
