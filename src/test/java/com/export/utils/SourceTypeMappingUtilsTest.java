package com.export.utils;

import com.export.base.utils.SourceMappingUtils;

public class SourceTypeMappingUtilsTest {
	public static void main(String[] args) {
		try {
			System.out.println("inputServiceName = "
					+ SourceMappingUtils.getInputServiceName("DB"));
			System.out.println("sourceConfigurationName = "
					+ SourceMappingUtils.getSourceConfigurationName("DB"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
