package com.export.utils;

import com.export.base.utils.DbmsMappingUtils;

public class DbmsMappingUtilsTest {
	public static void main(String[] args) {
		
		try {
			String driverClassName;
			
			driverClassName = "";
			driverClassName = DbmsMappingUtils.getDriverClassName("mysql");
			System.out.println(driverClassName);

			driverClassName = "";
			driverClassName = DbmsMappingUtils.getDriverClassName("oracle");
			System.out.println(driverClassName);

			driverClassName = "";
			driverClassName = DbmsMappingUtils.getDriverClassName("sqlserver");
			System.out.println(driverClassName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {	
			String connectionUrlTemplate;
			
			connectionUrlTemplate = "";
			connectionUrlTemplate = DbmsMappingUtils.getConnectionUrl("mysql", "localhost", "3306", "test");
			System.out.println(connectionUrlTemplate);

			connectionUrlTemplate = "";
			connectionUrlTemplate = DbmsMappingUtils.getConnectionUrl("oracle", "127.0.0.1", "1521", "NBSDEV");
			System.out.println(connectionUrlTemplate);

			connectionUrlTemplate = "";
			connectionUrlTemplate = DbmsMappingUtils.getConnectionUrl("sqlserver", "localhost", "3306", "test");
			System.out.println(connectionUrlTemplate);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
