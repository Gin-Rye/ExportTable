package com.export.utils;

import java.util.Map;

import com.export.data.input.SourceConfiguration;
import com.export.exception.BussinessException;

public class JdbcDriverUtils {
	
	static public void checkProperties(SourceConfiguration sourceConfiguration) throws BussinessException {
		String[] propertyNames = {"dbms", "host", "port", "instance", "username", "password"};
		for(String propertyName : propertyNames) {
			if(!sourceConfiguration.getProperties().containsKey(propertyName)) {
				throw new BussinessException("Lack property: " + propertyName);
			}
		}
	}
	
	static public String getDriverName(SourceConfiguration sourceConfiguration) throws BussinessException {
		String dbms = sourceConfiguration.getProperties().get("dbms");
		if(dbms.equalsIgnoreCase("mysql")) {
			return "com.mysql.jdbc.Driver";
		} else if(dbms.equalsIgnoreCase("oracle")) {
			return "oracle.jdbc.driver.OracleDriver";
		} else {
			throw new BussinessException("No such driver name");
		}
	}
	
	static public String getConnectionUrl(SourceConfiguration sourceConfiguration) throws BussinessException {
		Map<String, String> properties = sourceConfiguration.getProperties();
		String dbms = properties.get("dbms");
		String host = properties.get("host");
		String port = properties.get("port");
		String instance = properties.get("instance");
		if(dbms.equalsIgnoreCase("mysql")) {
			return "jdbc:mysql://" + host + ":" + port + "/" + instance;
		} else if(dbms.equalsIgnoreCase("oracle")) {
			return "jdbc:oracle:thin:@//" + host + ":" + port + "/" + instance;
		} else {
			throw new BussinessException("No such connection url");
		}
	}
}
