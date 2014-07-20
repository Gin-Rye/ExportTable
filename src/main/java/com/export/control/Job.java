package com.export.control;

import com.export.model.configuration.SinkConfiguration;
import com.export.model.configuration.SourceConfiguration;
import com.export.model.input.query.QueryCommand;

public class Job {
	private SourceConfiguration sourceConfiguration;
	private SinkConfiguration sinkConfiguration;
	private QueryCommand command;
	
	public Job(SourceConfiguration sourceConfiguration,
			SinkConfiguration sinkConfiguration,
			QueryCommand command) {
		this.sourceConfiguration = sourceConfiguration;
		this.sinkConfiguration = sinkConfiguration;
		this.command = command;
	}
	
	public SourceConfiguration getSourceConfiguration() {
		return sourceConfiguration;
	}
	public SinkConfiguration getSinkConfiguration() {
		return sinkConfiguration;
	}
	public QueryCommand getCommand() {
		return command;
	}
}
