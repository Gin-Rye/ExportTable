package com.export.data.output;

import com.export.data.input.InputService;
import com.export.data.input.InputServiceFactory;
import com.export.data.input.SourceConfiguration;
import com.export.data.input.SourceConfigurationFactory;
import com.export.data.output.OutputService;
import com.export.data.output.OutputServiceFactory;
import com.export.data.output.SinkConfiguration;
import com.export.data.output.SinkConfigurationFactory;
import com.export.data.store.ResultStore;

public class H2XmlFileOutputServiceTest {
	public static void main(String[] args) {
		System.out.println("start...");
		try {
			SourceConfiguration sourceConfiguration = 
					SourceConfigurationFactory.getSourceConfiguration("./res/source.conf");
			InputService inputService = 
					InputServiceFactory.getInputService(sourceConfiguration);
			SinkConfiguration sinkConfiguration = 
					SinkConfigurationFactory.getSinkConfiguration("./res/sink.conf");
			OutputService outputService = 
					OutputServiceFactory.getOutputService(sinkConfiguration);
			//for(int i = 0; i < 20; i++) {
				//System.out.println("Begin: " + i);
				ResultStore resultStore = inputService.inputData(
					"select * from ref_bsp t");
				outputService.effectiveOutputData("ref_bsp", resultStore);
				resultStore.close();
				//System.out.println("Finish: " + i);
			//}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("stop...");
	}
}
