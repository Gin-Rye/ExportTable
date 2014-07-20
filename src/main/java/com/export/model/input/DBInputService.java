package com.export.model.input;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.export.base.exception.BusinessException;
import com.export.model.configuration.DBSourceConfiguration;
import com.export.model.input.connection.DBConnectionPoolManager;
import com.export.model.input.query.SQLQueryCommand;
import com.export.model.store.DBResultStore;
import com.export.model.store.ResultStore;

public class DBInputService extends InputService<SQLQueryCommand> {
	
	public DBInputService(DBSourceConfiguration dbSourceConfiguration) throws BusinessException {
		super(dbSourceConfiguration);
	}
	
	@Override
	public synchronized ResultStore inputData(SQLQueryCommand command) throws BusinessException {
		try {
			DBSourceConfiguration dbSourceConfiguration = (DBSourceConfiguration) sourceConfiguration;
			Connection connection = DBConnectionPoolManager.getConnection(dbSourceConfiguration);
			Statement stmt = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery(command.getSql());
			DBResultStore dbResultStore = new DBResultStore(rs);
			dbResultStore.setTableName(command.getTableName());
			return dbResultStore;
		} catch(SQLException e) {
			throw new BusinessException(e);
		} catch(BusinessException e) {
			throw e;
		}
	}
}
