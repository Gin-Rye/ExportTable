package com.export.view.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.export.base.log.Logger;
import com.export.control.Job;
import com.export.control.factory.ConfigurationFactory;
import com.export.control.factory.InputServiceFactory;
import com.export.control.factory.OutputServiceFactory;
import com.export.model.configuration.Configuration;
import com.export.model.input.DBInputService;
import com.export.model.input.InputService;
import com.export.model.input.query.QueryCommand;
import com.export.model.input.query.SQLQueryCommand;
import com.export.model.output.OutputService;

public class BatchUI {
	public static void main(String[] args) {
		try {
			ExecutorService executorService = Executors.newFixedThreadPool(3);
			
			String sourceConfigurationFilePath = "./conf/source.conf.xml";
			String sinkConfigurationFilePath = "./conf/sink.conf.xml";
			List<Configuration> sourceConfigurationList = 
				ConfigurationFactory.getConfiguration(sourceConfigurationFilePath);
			List<InputService> inputServiceList = new ArrayList<InputService>();
			for(Configuration sourceConfiguration : sourceConfigurationList) {
				InputService inputService = InputServiceFactory.getInputService(sourceConfiguration);
				inputServiceList.add(inputService);
			}
			List<Configuration> sinkConfigurationList = 
				ConfigurationFactory.getConfiguration(sinkConfigurationFilePath);
			List<OutputService> outputServiceList = new ArrayList<OutputService>();
			for(Configuration sinkConfiguration : sinkConfigurationList) {
				OutputService outputService = OutputServiceFactory.getOutputService(sinkConfiguration);
				outputServiceList.add(outputService);
			}
			String[] tableNames = {"tb_stock_history_market", 
					"tb_stock_history_stock",
					"tb_stock_info_market",
					"tb_stock_info_market_code",
					"tb_stock_info_stock",
					"tb_stock_realtime_market",
					"tb_stock_realtime_stock"};
			String[] sqls = new String[tableNames.length];
			for(int i = 0; i < sqls.length; i++) {
				String sql = "select * from " + tableNames[i];
				sqls[i] = sql;
			}
			for(int i = 0; i < sqls.length; i++) {
				String tableName = tableNames[i];
				String sql = sqls[i];
				for(InputService inputService : inputServiceList) {
					QueryCommand command;
					if(inputService instanceof DBInputService) {
						command = new SQLQueryCommand(sql);
					} else {
						command = new QueryCommand();
					}
					for(OutputService outputService : outputServiceList) {
						Job job = new Job(command, tableName, inputService, outputService);
						executorService.submit(job);
					}
				}
			}
			executorService.shutdown();
		} catch(Exception e) {
			Logger.log(e);
		}
	}
}
