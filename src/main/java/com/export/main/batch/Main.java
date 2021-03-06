package com.export.main.batch;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.export.base.log.Logger;
import com.export.control.Job;
import com.export.control.batch.BatchJobManager;
import com.export.control.batch.factory.SinkConfigurationFactory;
import com.export.control.batch.factory.SourceConfigurationFactory;
import com.export.model.configuration.SinkConfiguration;
import com.export.model.configuration.SourceConfiguration;
import com.export.model.input.query.QueryCommand;
import com.export.model.input.query.SQLQueryCommand;

public class Main {
	public static String sourceConfigurationFilePath = "./batch/source.conf.xml";
	public static String sinkConfigurationFilePath = "./batch/sink.conf.xml";
	public static final String commandsFilePath = "./batch/sql.command.xml";
	
	public static void main(String[] args) {
		try {
			BatchJobManager jobManager = new BatchJobManager();
			List<SourceConfiguration> sourceConfigurationList = 
				SourceConfigurationFactory.getSourceConfiguration(sourceConfigurationFilePath);
			List<SinkConfiguration> sinkConfigurationList = 
				SinkConfigurationFactory.getSinkConfiguration(sinkConfigurationFilePath);
			List<SQLQueryCommand> commands = new LinkedList<SQLQueryCommand>();
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(commandsFilePath));
			Element root = document.getRootElement();
			Iterator<?> iterator = root.elementIterator();
			while(iterator.hasNext()) {
				Element element = (Element) iterator.next();
				String tableName = element.elementText("tableName");
				String sql = element.elementText("sql");
				SQLQueryCommand command = new SQLQueryCommand(tableName, sql);
				commands.add(command);
			}
			for(SourceConfiguration sourceConfiguration : sourceConfigurationList) {
				for(SinkConfiguration sinkConfiguration : sinkConfigurationList) {
					for(QueryCommand command : commands) {
						Job job = new Job(sourceConfiguration, sinkConfiguration, command);
						jobManager.add(job);
					}
				}
			}
			jobManager.start();
		} catch(Exception e) {
			Logger.log(e);
		}
	}
}
