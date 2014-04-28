package com.export.data.output;

import java.util.List;

import com.export.control.factory.InputServiceFactory;
import com.export.control.factory.OutputServiceFactory;
import com.export.control.factory.ConfigurationFactory;
import com.export.model.configuration.Configuration;
import com.export.model.input.InputService;
import com.export.model.input.query.QueryCommand;
import com.export.model.input.query.SQLQueryCommand;
import com.export.model.output.OutputService;
import com.export.model.store.ResultStore;

public class H2XmlFileOutputServiceTest {
	public static String sourceConfigurationFilePath = "./conf/source.conf.xml";
	public static String sinkConfigurationFilePath = "./conf/sink.conf.xml";
	
	public static void main(String[] args) {
		test_1();
	}
	
	public static void test_1() {
		System.out.println("[start]");
		try {
			List<Configuration> sourceConfigurationList = 
				ConfigurationFactory.getConfiguration(sourceConfigurationFilePath);
			List<Configuration> sinkConfigurationList = 
				ConfigurationFactory.getConfiguration(sinkConfigurationFilePath);
			for(Configuration sourceConfiguration : sourceConfigurationList) {
				String sql = "select * from tb_stock_realtime_stock t";
				QueryCommand command = new SQLQueryCommand(sql);
				InputService inputService = InputServiceFactory.getInputService(sourceConfiguration);
				ResultStore resultStore = inputService.inputData(command, "tb_stock");
				for(Configuration sinkConfiguration : sinkConfigurationList) {
					OutputService outputService = OutputServiceFactory.getOutputService(sinkConfiguration);
					
					resultStore.moveBeforeFirst();
					resultStore.setTableName(resultStore.getTableName() + "_common");
					outputService.outputData(resultStore);
					System.out.println("common finish");
					
					resultStore.moveBeforeFirst();
					resultStore.setTableName(resultStore.getTableName() + "_effective");
					outputService.effectiveOutputData(resultStore);
					System.out.println("effective finish");
				}
				resultStore.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("[stop]");
	}
}
