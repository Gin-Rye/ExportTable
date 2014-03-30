package com.export.data.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.export.data.store.ResultStore;
import com.export.exception.BussinessException;
import com.export.utils.DateUtils;

public class H2XmlFileOutputService extends H2XmlOutputService implements FileWriterInterface {

	public H2XmlFileOutputService(SinkConfiguration sinkConfiguration) {
		super(sinkConfiguration);
	}
	
	@Override
	public void outputData(String tableName, ResultStore resultStore) throws BussinessException {
		String path = generateOutputPath(tableName);
		outputToFile(path, tableName, resultStore);
	}
	
	@Override
	public void effectiveOutputData(String tableName, ResultStore resultStore) throws BussinessException {
		String path = generateOutputPath(tableName);
		effectiveOutputToFile(path, tableName, resultStore);
	}
	
	@Override
	public void outputToFile(String path, String tableName,
			ResultStore resultStore) throws BussinessException {
		try {
			Document document = generateXmlDocument(tableName, resultStore);
			Writer fileWriter = new BufferedWriter(new FileWriter(path, false));
			OutputFormat outputFormat = new OutputFormat();
			outputFormat.setEncoding("UTF-8");
			outputFormat.setNewlines(true);
			outputFormat.setIndent(true);
			XMLWriter writer = new XMLWriter(fileWriter, outputFormat);
			writer.write(document);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			throw new BussinessException(e);
		}
	}

	@Override
	public void effectiveOutputToFile(String path, String tableName,
			ResultStore resultStore) throws BussinessException {
		try {
			List<String> columnNameList = getColumnNames(resultStore);
			Writer writer = new BufferedWriter(new FileWriter(path, false));
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
			writer.write("<dataset>\r\n");
			writer.flush();
			resultStore.moveBeforeFirst();
			while (resultStore.hasNext()) {
				resultStore.next();
				writer.write("   <" + tableName);
				for (String columnName : columnNameList) {
					writer.write(" " + columnName + "=");
					Object object = resultStore.getColumnData(columnName);
					String value = "\"";
					if(object != null) {
						if (object instanceof Date) {
							value += DateUtils.dateToString((Date) object,
									"yyyy-MM-dd HH:mm:ss");
						} else {
							value += object.toString();
						}
					}
					value += "\"";
					writer.write(value);
				}
				writer.write("/>\r\n");
				writer.flush();
			}
			writer.write("</dataset>\r\n");
			writer.flush();
			writer.close();
		} catch(BussinessException e) {
			throw e;
		} catch (IOException e) {
			throw new BussinessException(e);
		}
	}
	
	private String generateOutputPath(String tableName) {
		String path = sinkConfiguration.getProperties().get("destination-dir")
				+ "/" + tableName + ".h2.xml";
		return path;
	}
}
