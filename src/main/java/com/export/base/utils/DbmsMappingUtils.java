package com.export.base.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.export.base.exception.BusinessException;
import com.export.base.log.Logger;

public class DbmsMappingUtils {
	
	static private Map<String, String> driverClassNameMap = new HashMap<String, String>();
	static private Map<String, String> connectionUrlTemplateMap = new HashMap<String, String>();
	static private String dbmsMappingFilePath = "./res/dbms.mapping.xml";
	
	static {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(
					new File(dbmsMappingFilePath));
			Element root = document.getRootElement();
			Iterator<?> iterator = root.elementIterator();
			while(iterator.hasNext()) {
				Element mapping = (Element) iterator.next();
				String dbms = mapping.elementTextTrim("dbms").toUpperCase();
				String driverClassName = mapping.elementTextTrim("driverClassName");
				String connectionUrlTemplate = mapping.elementTextTrim("connectionUrlTemplate");
				driverClassNameMap.put(dbms, driverClassName);
				connectionUrlTemplateMap.put(dbms, connectionUrlTemplate);
			}
		} catch(DocumentException e) {
			Logger.log(e);
		}
	}
	
	public static String getDriverClassName(String dbms) throws BusinessException {
		
		dbms = dbms.toUpperCase();
		if(driverClassNameMap.containsKey(dbms)) {
			return driverClassNameMap.get(dbms);
		} else {
			throw new BusinessException("No such driver");
		}
	}
	
	public static String getConnectionUrl(String dbms, 
			String host, String port, String instance) throws BusinessException {
		
		dbms = dbms.toUpperCase();
		if(connectionUrlTemplateMap.containsKey(dbms)) {
			String connectionUrl = connectionUrlTemplateMap.get(dbms);
			connectionUrl = connectionUrl.replace("${host}", host);
			connectionUrl = connectionUrl.replace("${port}", port);
			connectionUrl = connectionUrl.replace("${instance}", instance);
			return connectionUrl;
		} else {
			throw new BusinessException("No such connection URL");
		}
	}
}
