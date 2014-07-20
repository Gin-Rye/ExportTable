package com.export.utils;

import com.export.base.utils.SinkMappingUtils;

public class SinkTypeMappingUtilsTest {
	public static void main(String[] args) {
		try {
			System.out.println("outputServiceName = "
					+ SinkMappingUtils.getOutputServiceName("H2XMLFile"));
			System.out.println("sinkConfigurationName = "
					+ SinkMappingUtils.getSinkConfigurationName("H2XMLFile"));
			

			System.out.println("outputServiceName = "
					+ SinkMappingUtils.getOutputServiceName("XLSFile"));
			System.out.println("sinkConfigurationName = "
					+ SinkMappingUtils.getSinkConfigurationName("XLSFile"));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
