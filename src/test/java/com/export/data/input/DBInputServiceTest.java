package com.export.data.input;

import java.util.List;

import com.export.control.batch.factory.InputServiceFactory;
import com.export.control.batch.factory.SourceConfigurationFactory;
import com.export.model.configuration.SourceConfiguration;
import com.export.model.input.InputService;
import com.export.model.input.query.QueryCommand;
import com.export.model.input.query.SQLQueryCommand;
import com.export.model.store.ResultStore;

public class DBInputServiceTest {
	public static String sourceConfigurationFilePath = "./conf/source.conf.xml";
	
	public static void main(String[] args) {
		test_1();
	}
	
	private static void test_1() {
		System.out.println("[start]");
		try {
			List<SourceConfiguration> sourceConfigurationList = 
				SourceConfigurationFactory.getSourceConfiguration(sourceConfigurationFilePath);
			for(SourceConfiguration sourceConfiguration : sourceConfigurationList) {
				InputService inputService = InputServiceFactory.getInputService(sourceConfiguration);
				String tableName = "tb_stock_info_market";
				String sql = "select * from tb_stock_info_market t";
				QueryCommand command = new SQLQueryCommand(tableName, sql);
				ResultStore resultStore = inputService.inputData(command);
				for(int i = 1; i <= resultStore.getColumnCount(); i++) {
					System.out.print(resultStore.getColumnName(i) + "	");
				}
				System.out.println();
				while(resultStore.hasNext()) {
					resultStore.next();
					for(int i = 0; i < resultStore.getColumnCount(); i++) {
						System.out.print(resultStore.getColumnData(i) + "	");
					}
				}
				System.out.println();
				resultStore.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("[stop]");
	}
}
