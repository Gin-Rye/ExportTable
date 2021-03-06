package com.export.model.store;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.export.base.exception.BusinessException;

public class DBResultStore extends ResultStore {
	private ResultSet resultSet;
	private List<String> columnNames = new ArrayList<String>();
	private List<Class<?>> columnClasses = new ArrayList<Class<?>>();
	private int size;
	
	public DBResultStore(ResultSet resultSet) throws BusinessException {
		try {
			this.resultSet = resultSet;
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			for(int column = 1; column <= resultSetMetaData.getColumnCount(); column++) {
				String columnName = resultSetMetaData.getColumnLabel(column);
				columnNames.add(columnName);
				String columnClassName = 
						resultSetMetaData.getColumnClassName(column);
				Class<?> columnClass = Class.forName(columnClassName);
				columnClasses.add(columnClass);
			}
			resultSet.last();
			size = resultSet.getRow();
			resultSet.beforeFirst();
		} catch(SQLException e) {
			throw new BusinessException(e);
		} catch(ClassNotFoundException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public String getColumnName(int column) {
		return columnNames.get(column - 1);
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return columnClasses.get(column - 1);
	}
	
	@Override
	public List<String> getColumnNames() {
		return new ArrayList<String>(columnNames);
	}
	
	@Override
	public List<Class<?>> getColumnClasses() {
		return new ArrayList<Class<?>>(columnClasses);
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void close() throws BusinessException {
		try {
			resultSet.close();
		} catch(SQLException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public boolean isClosed() throws BusinessException {
		try {
			return resultSet.isClosed();
		} catch(SQLException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public int getCursor() throws BusinessException {
		try {
			return resultSet.getRow();
		} catch(SQLException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void setCursor(int row) throws BusinessException {
		try {
			resultSet.absolute(row + 1);
		} catch(SQLException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void moveBeforeFirst() throws BusinessException {
		try {
			resultSet.beforeFirst();
		} catch(SQLException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void next() throws BusinessException {
		try {
			resultSet.next();
		} catch(SQLException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public boolean hasNext() throws BusinessException {
		try {
			if(size == 0) {
				return false;
			} else {
				return !resultSet.isLast();
			}
		} catch(SQLException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Object getColumnData(int column) throws BusinessException {
		try {
			return resultSet.getObject(column + 1);
		} catch(SQLException e) {
			throw new BusinessException(e);
		}
	}
	
	@Override
	public Object getColumnData(String columnName) throws BusinessException {
		try {
			return resultSet.getObject(columnName);
		} catch(SQLException e) {
			throw new BusinessException(e);
		}
	}
}
