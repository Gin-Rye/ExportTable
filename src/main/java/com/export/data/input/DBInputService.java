package com.export.data.input;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.export.data.store.DBResultStore;
import com.export.data.store.ResultStore;
import com.export.exception.BussinessException;
import com.export.utils.JdbcDriverUtils;

public class DBInputService extends InputService {
	private Connection connection;
	
	public DBInputService(SourceConfiguration sourceConfiguration) throws BussinessException {
		super(sourceConfiguration);
		try {
			connect();
		} catch(BussinessException e) {
			throw e;
		}
	}
	
	private void connect() throws BussinessException {
		if(connection != null) {
			return;
		}
		try {
			String driverName = JdbcDriverUtils.getDriverName(sourceConfiguration);
			Class.forName(driverName);
			String url = JdbcDriverUtils.getConnectionUrl(sourceConfiguration);
			String username = sourceConfiguration.getProperties().get("username");
			String password = sourceConfiguration.getProperties().get("password");
			connection = DriverManager.getConnection(url, username, password);
		} catch(ClassNotFoundException e) {
			throw new BussinessException(e);
		} catch(SQLException e) {
			throw new BussinessException(e);
		} catch(BussinessException e) {
			throw e;
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
			return new DBResultStore(rs);
		} catch(SQLException e) {
			throw new BussinessException(e);
		} catch(BussinessException e) {
			throw e;
		}
	}
}
