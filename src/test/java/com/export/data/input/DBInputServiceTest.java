package com.export.data.input;

import com.export.data.input.InputService;
import com.export.data.input.InputServiceFactory;
import com.export.data.input.SourceConfiguration;
import com.export.data.input.SourceConfigurationFactory;
import com.export.data.store.ResultStore;

public class DBInputServiceTest {
	public static void main(String[] args) {
		test_1();
	}
	
	private static void test_1() {
		System.out.println("Test_1 start...");
		try {
			SourceConfiguration sourceConfiguration = 
					SourceConfigurationFactory.getSourceConfiguration("./res/source.conf");
			InputService inputService = InputServiceFactory.getInputService(sourceConfiguration);
			ResultStore store = inputService.inputData(
					"select * from tb_stock_info_stock");
			for(int column = 1; column <= store.getColumnCount(); column++) {
				System.out.print(store.getColumnName(column) + " ");
			}
			System.out.println();
			while(store.hasNext()) {
				store.next();
				for(int column = 1; column <= store.getColumnCount(); column++) {
					System.out.print(store.getColumnData(column));
				}
				System.out.println();
			}
			store.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Test_1 stop...");
	}
}
