package com.export.control.batch.factory;

import java.util.List;

import com.export.control.batch.factory.OutputServiceFactory;
import com.export.control.batch.factory.SinkConfigurationFactory;
import com.export.model.configuration.SinkConfiguration;
import com.export.model.output.OutputService;

public class OutputServiceFactoryTest {
	public static void main(String[] args) {
		try {
			String sinkConfiguraionFilePath = "./conf/sink.conf.xml";
			List<SinkConfiguration> sinkConfigurationList = 
				SinkConfigurationFactory.getSinkConfiguration(sinkConfiguraionFilePath);
			for(SinkConfiguration sinkConfiguration : sinkConfigurationList) {
				OutputService outputService = OutputServiceFactory.getOutputService(sinkConfiguration);
				System.out.println(outputService.getClass());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
