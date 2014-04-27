package com.export.control.factory;

import java.util.List;

import com.export.model.configuration.Configuration;
import com.export.model.input.InputService;

public class InputServiceFactoryTest {
	public static void main(String[] args) {
		try {
			String sourceConfiguraionFilePath = "./conf/source.conf.xml";
			List<Configuration> sourceConfigurationList = 
				ConfigurationFactory.getConfiguration(sourceConfiguraionFilePath);
			for(Configuration sourceConfiguration : sourceConfigurationList) {
				InputService inputService = InputServiceFactory.getInputService(sourceConfiguration);
				System.out.println(inputService.getClass());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
