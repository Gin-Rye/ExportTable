package com.export.utils;

import com.export.base.utils.SinkTypeMappingUtils;

public class SinkTypeMappingUtilsTest {
	public static void main(String[] args) {
		try {
			String outputServiceName;
			
			outputServiceName = "";
			outputServiceName = SinkTypeMappingUtils.getOutputServiceName("H2XmlFile");
			System.out.println(outputServiceName);

			outputServiceName = "";
			outputServiceName = SinkTypeMappingUtils.getOutputServiceName("DB");
			System.out.println(outputServiceName);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
