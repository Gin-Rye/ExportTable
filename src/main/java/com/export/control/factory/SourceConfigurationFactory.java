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
import com.export.base.utils.SourceMappingUtils;
import com.export.model.configuration.SourceConfiguration;

public class SourceConfigurationFactory {
	public static List<SourceConfiguration> getSourceConfiguration(
			String sourceConfigurationFilePath) throws BusinessException {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(
					new File(sourceConfigurationFilePath));
			Element root = document.getRootElement();
			Iterator<?> iterator = root.elementIterator();
			List<SourceConfiguration> sourceConfigurationList = 
				new ArrayList<SourceConfiguration>();
			while(iterator.hasNext()) {
				Element element = (Element) iterator.next();
				String type = element.elementTextTrim("type").toUpperCase();
				String sourceConfigurationName = SourceMappingUtils.getSourceConfigurationName(type);
				Class<?> sourceConfigurationClass = Class.forName(sourceConfigurationName);
				Constructor<?> sourceConfigurationConstructor = 
					sourceConfigurationClass.getConstructor(String.class);
				SourceConfiguration sourceConfiguration = 
					(SourceConfiguration) sourceConfigurationConstructor.newInstance(type);
				Element properties = element.element("properties");
				Iterator<?> propertyIterator = properties.elementIterator();
				while(propertyIterator.hasNext()) {
					Element property = (Element) propertyIterator.next();
					String propertyName = property.elementText("name");
					String propertyValue = property.elementText("value");
					sourceConfiguration.setProperty(propertyName, propertyValue);
				}
				sourceConfigurationList.add(sourceConfiguration);
			}
			return sourceConfigurationList;
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
