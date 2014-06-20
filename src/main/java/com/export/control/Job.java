package com.export.control;

import com.export.model.configuration.Configuration;
import com.export.model.input.query.QueryCommand;

public class Job {
	private Configuration sourceConfiguration;
	private Configuration sinkConfiguration;
	private QueryCommand command;
	
	public Job(Configuration sourceConfiguration,
			Configuration sinkConfiguration,
			QueryCommand command) {
		this.sourceConfiguration = sourceConfiguration;
		this.sinkConfiguration = sinkConfiguration;
		this.command = command;
	}
	
	public Configuration getSourceConfiguration() {
		return sourceConfiguration;
	}
	public Configuration getSinkConfiguration() {
		return sinkConfiguration;
	}
	public QueryCommand getCommand() {
		return command;
	}
}
