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
			return "Oracle.jdbc.driver.OracleDriver";
		} else {
			throw new BussinessException("No such driver name");
		}
	}
	
	static public String getConnectionUrl(SourceConfiguration sourceConfiguration) throws BussinessException {
		Map<String, String> properties = sourceConfiguration.getProperties();
		String dbms = properties.get("dbms");
		String host = properties.get("host");
		String port = properties.get("port");
		String dbName = properties.get("instance");
		if(dbms.equalsIgnoreCase("mysql")) {
			return "jdbc:mysql://" + host + ":" + port + "/" + dbName;
		} else if(dbms.equalsIgnoreCase("oracle")) {
			return "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName;
		} else {
			throw new BussinessException("No such connection url");
		}
	}
}
