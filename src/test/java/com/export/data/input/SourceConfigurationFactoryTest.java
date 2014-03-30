package com.export.data.input;

import com.export.data.input.SourceConfiguration;
import com.export.data.input.SourceConfigurationFactory;

public class SourceConfigurationFactoryTest {
	public static void main(String[] args) {
		test_1();
	}
	
	private static void test_1() {
		System.out.println("Test_1 start...");
		try {
			SourceConfiguration sourceConfiguration = 
					SourceConfigurationFactory.getSourceConfiguration("./res/source.conf");
			System.out.println("type = " + sourceConfiguration.getSourceType());
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Test_1 stop...");
	}
}
