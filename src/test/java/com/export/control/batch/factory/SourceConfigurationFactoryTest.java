package com.export.control.batch.factory;

import java.util.List;

import com.export.control.batch.factory.SourceConfigurationFactory;
import com.export.model.configuration.SourceConfiguration;

public class SourceConfigurationFactoryTest {
	public static void main(String[] args) {
		try {
			String sourceConfiguraionFilePath = "./batch/source.conf.xml";
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
