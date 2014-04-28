package com.export.control;

import com.export.base.log.Logger;
import com.export.model.input.InputService;
import com.export.model.input.query.QueryCommand;
import com.export.model.output.OutputService;
import com.export.model.store.ResultStore;

public class Job<T extends QueryCommand> implements Runnable {
	private InputService<T> inputService;
	private OutputService outputService;
	private T command;
	private String tableName;
	
	public Job(T command, String tableName, InputService<T> inputService, OutputService outputService) {
		this.command = command;
		this.tableName = tableName;
		this.inputService = inputService;
		this.outputService = outputService;
	}
	
	@Override
	public void run() {
		try {
			Logger.log("[info] start: " + tableName);
			ResultStore resultStore = inputService.inputData(command, tableName);
			outputService.outputData(resultStore);
			resultStore.close();
			Logger.log("[info] finish: " + tableName);
		} catch(Exception e) {
			Logger.log(e);
		}
	}
}
