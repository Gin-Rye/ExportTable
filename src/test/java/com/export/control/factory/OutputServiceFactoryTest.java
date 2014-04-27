package com.export.control.factory;

import java.util.List;

import com.export.model.configuration.Configuration;
import com.export.model.output.OutputService;

public class OutputServiceFactoryTest {
	public static void main(String[] args) {
		try {
			String sinkConfiguraionFilePath = "./conf/sink.conf.xml";
			List<Configuration> sinkConfigurationList = 
				ConfigurationFactory.getConfiguration(sinkConfiguraionFilePath);
			for(Configuration sinkConfiguration : sinkConfigurationList) {
				OutputService outputService = OutputServiceFactory.getOutputService(sinkConfiguration);
				System.out.println(outputService.getClass());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
