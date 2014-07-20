package com.export.control.factory;

import java.util.List;

import com.export.model.configuration.SourceConfiguration;

public class SourceConfigurationFactoryTest {
	public static void main(String[] args) {
		try {
			String sourceConfiguraionFilePath = "./conf/source.conf.xml";
			List<SourceConfiguration> sourceConfigurationList = 
				SourceConfigurationFactory.getSourceConfiguration(sourceConfiguraionFilePath);
			for(SourceConfiguration sourceConfiguration : sourceConfigurationList) {
				System.out.println(sourceConfiguration.getClass());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
