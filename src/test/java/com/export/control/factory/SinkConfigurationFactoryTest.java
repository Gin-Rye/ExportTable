package com.export.control.factory;

import java.util.List;
import java.util.Map;

import com.export.model.configuration.Configuration;

public class SinkConfigurationFactoryTest {
	public static void main(String[] args) {
		try {
			String sinkConfiguraionFilePath = "./conf/sink.conf.xml";
			List<Configuration> sinkConfigurationList = 
				ConfigurationFactory.getConfiguration(sinkConfiguraionFilePath);
			for(Configuration sinkConfiguration : sinkConfigurationList) {
				System.out.println("sink type: " + sinkConfiguration.getType());
				System.out.println("properties: ");
				Map<String, String> properties = sinkConfiguration.getProperties();
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
