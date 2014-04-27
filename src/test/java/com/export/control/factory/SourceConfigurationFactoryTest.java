package com.export.control.factory;

import java.util.List;
import java.util.Map;

import com.export.control.factory.SourceConfigurationFactory;
import com.export.model.configuration.Configuration;

public class SourceConfigurationFactoryTest {
	public static void main(String[] args) {
		try {
			String sourceConfiguraionFilePath = "./conf/source.conf.xml";
			List<Configuration> sourceConfigurationList = 
				SourceConfigurationFactory.getSourceConfiguration(sourceConfiguraionFilePath);
			for(Configuration sourceConfiguration : sourceConfigurationList) {
				System.out.println("source type: " + sourceConfiguration.getType());
				System.out.println("properties: ");
				Map<String, String> properties = sourceConfiguration.getProperties();
				for(String key : properties.keySet()) {
					String value = properties.get(key);
					System.out.println("   " + key + " = " + value);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
