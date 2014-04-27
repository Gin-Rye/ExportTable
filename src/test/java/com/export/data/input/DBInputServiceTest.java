package com.export.data.input;

import java.util.List;

import com.export.control.factory.InputServiceFactory;
import com.export.control.factory.SourceConfigurationFactory;
import com.export.model.configuration.Configuration;
import com.export.model.input.InputService;
import com.export.model.store.ResultStore;

public class DBInputServiceTest {
	public static String sourceConfigurationFilePath = "./conf/source.conf.xml";
	
	public static void main(String[] args) {
		test_1();
	}
	
	private static void test_1() {
		System.out.println("[start]");
		try {
			List<Configuration> sourceConfigurationList = 
				SourceConfigurationFactory.getSourceConfiguration(sourceConfigurationFilePath);
			for(Configuration sourceConfiguration : sourceConfigurationList) {
				InputService inputService = InputServiceFactory.getInputService(sourceConfiguration);
				String sql = "select * from tb_user t";
				ResultStore resultStore = inputService.inputData(sql);
				for(int i = 1; i <= resultStore.getColumnCount(); i++) {
					System.out.print(resultStore.getColumnName(i) + "	");
				}
				System.out.println();
				while(resultStore.hasNext()) {
					resultStore.next();
					for(int i = 1; i <= resultStore.getColumnCount(); i++) {
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
