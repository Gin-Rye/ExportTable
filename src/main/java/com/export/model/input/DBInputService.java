package com.export.model.input;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.export.base.exception.BussinessException;
import com.export.base.utils.DbmsMappingUtils;
import com.export.model.configuration.Configuration;
import com.export.model.store.DBResultStore;
import com.export.model.store.ResultStore;

public class DBInputService extends ConnectionInputService {
	private Connection connection;
	
	public DBInputService(Configuration sourceConfiguration) throws BussinessException {
		super(sourceConfiguration);
	}

	@Override
	public void connect() throws BussinessException {
		if(connection != null) {
			return;
		}
		try {
			Map<String, String> properties = sourceConfiguration.getProperties();
			String dbms = properties.get("dbms");
			String host = properties.get("host");
			String port = properties.get("port");
			String instance = properties.get("instance");
			String username = properties.get("username");
			String password = properties.get("password");
			String driverClassName = DbmsMappingUtils.getDriverClassName(dbms);
			Class.forName(driverClassName);
			String connectionUrl = DbmsMappingUtils.getConnectionUrl(dbms, host, port, instance);
			connection = DriverManager.getConnection(connectionUrl, username, password);
		} catch(ClassNotFoundException e) {
			throw new BussinessException(e);
		} catch(SQLException e) {
			throw new BussinessException(e);
		} catch(BussinessException e) {
			throw e;
		}
	}
	
	@Override
	public void close() throws BussinessException {
		if(connection == null) {
			throw new BussinessException("Unconnect to DBMS");
		}
		try {
			connection.close();
		} catch(SQLException e) {
			throw new BussinessException(e);
		}
	}
	
	@Override
	public ResultStore inputData(String command) throws BussinessException {
		if(connection == null) {
			throw new BussinessException("Unconnect to DBMS");
		}
		try {
			Statement stmt = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(command);
			DBResultStore dbResultStore = new DBResultStore(rs);
			return dbResultStore;
		} catch(SQLException e) {
			throw new BussinessException(e);
		} catch(BussinessException e) {
			throw e;
		}
	}
}
