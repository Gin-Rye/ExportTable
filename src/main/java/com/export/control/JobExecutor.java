package com.export.control;

import com.export.base.exception.BussinessException;
import com.export.base.log.Logger;
import com.export.control.factory.InputServiceFactory;
import com.export.control.factory.OutputServiceFactory;
import com.export.model.configuration.Configuration;
import com.export.model.input.InputService;
import com.export.model.input.query.QueryCommand;
import com.export.model.output.OutputService;
import com.export.model.store.ResultStore;

public class JobExecutor implements Runnable {
	private Job job;
	
	public JobExecutor(Job job) {
		this.job = job;
	}
	
	public Job getJob() {
		return job;
	}

	@Override
	public void run() {
		try {
			InputService inputService = InputServiceFactory.getInputService(
					job.getSourceConfiguration());
			OutputService outputService = OutputServiceFactory.getOutputService(
					job.getSinkConfiguration());
			ResultStore resultStore = inputService.inputData(
					job.getCommand());
			outputService.outputData(resultStore);
		} catch(Exception e) {
			Logger.log(e);
		}
	}
}
