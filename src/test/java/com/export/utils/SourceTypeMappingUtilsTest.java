package com.export.utils;

import com.export.base.utils.SourceTypeMappingUtils;

public class SourceTypeMappingUtilsTest {
	public static void main(String[] args) {
		try {
			String inputServiceName;
			
			inputServiceName = "";
			inputServiceName = SourceTypeMappingUtils.getInputServiceName("DB");
			System.out.println(inputServiceName);

			inputServiceName = "";
			inputServiceName = SourceTypeMappingUtils.getInputServiceName("H2XmlFile");
			System.out.println(inputServiceName);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
