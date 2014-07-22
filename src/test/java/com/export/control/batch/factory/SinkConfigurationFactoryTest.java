package com.export.control.batch.factory;

import java.util.List;

import com.export.control.batch.factory.SinkConfigurationFactory;
import com.export.model.configuration.SinkConfiguration;

public class SinkConfigurationFactoryTest {
	public static void main(String[] args) {
		try {
			String sinkConfiguraionFilePath = "./conf/sink.conf.xml";
			List<SinkConfiguration> sinkConfigurationList = 
				SinkConfigurationFactory.getSinkConfiguration(sinkConfiguraionFilePath);
			for(SinkConfiguration sinkConfiguration : sinkConfigurationList) {
				System.out.println(sinkConfiguration.getClass());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
