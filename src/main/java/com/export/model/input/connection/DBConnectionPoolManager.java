package com.export.model.input.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.export.base.exception.BusinessException;
import com.export.base.utils.DbmsMappingUtils;
import com.export.model.configuration.DBSourceConfiguration;

public class DBConnectionPoolManager {
	private static Map<String, Connection> connectionMap = new HashMap<String, Connection>();
	
	public static Connection getConnection(DBSourceConfiguration dbSourceConfiguration) throws BusinessException {
		try {

			String url = DbmsMappingUtils.getConnectionUrl(
					dbSourceConfiguration.getDbms(), 
					dbSourceConfiguration.getHost(), 
					dbSourceConfiguration.getPort(), 
					dbSourceConfiguration.getInstance());
			String username = dbSourceConfiguration.getUsername();
			String password = dbSourceConfiguration.getPassword();
			String mapKey = url + "|" + username + "|" + password;
			if(!connectionMap.containsKey(mapKey)) {
				String driverClassName = DbmsMappingUtils.getDriverClassName(
						dbSourceConfiguration.getDbms());
				Class.forName(driverClassName);
				Connection connection = DriverManager.getConnection(url, username, password);
				connectionMap.put(mapKey, connection);
			}
			return connectionMap.get(mapKey);
		} catch(BusinessException e) {
			throw new BusinessException(e);
		} catch (ClassNotFoundException e) {
			throw new BusinessException(e);
		} catch (SQLException e) {
			throw new BusinessException(e);
		}
	}
}
