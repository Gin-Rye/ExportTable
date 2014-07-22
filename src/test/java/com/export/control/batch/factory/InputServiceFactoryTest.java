package com.export.control.batch.factory;

import java.util.List;

import com.export.control.batch.factory.InputServiceFactory;
import com.export.control.batch.factory.SourceConfigurationFactory;
import com.export.model.configuration.SourceConfiguration;
import com.export.model.input.InputService;

public class InputServiceFactoryTest {
	public static void main(String[] args) {
		try {
			String sourceConfiguraionFilePath = "./conf/source.conf.xml";
			List<SourceConfiguration> sourceConfigurationList = 
				SourceConfigurationFactory.getSourceConfiguration(sourceConfiguraionFilePath);
			for(SourceConfiguration sourceConfiguration : sourceConfigurationList) {
				InputService<?> inputService = InputServiceFactory.getInputService(sourceConfiguration);
				System.out.println(inputService.getClass());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
