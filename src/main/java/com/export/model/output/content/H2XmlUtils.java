package com.export.model.output.content;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.export.base.exception.BussinessException;
import com.export.base.utils.DateUtils;
import com.export.model.store.ResultStore;

public class H2XmlUtils {
	public static Document generate(ResultStore resultStore) throws BussinessException {
		try {
			String tableName = resultStore.getTableName();
			List<String> columnNameList = resultStore.getColumnNames();
			Document document = DocumentFactory.getInstance().createDocument();
			document.addElement("dataset");
			Element root = document.getRootElement();
			resultStore.moveBeforeFirst();
			while (resultStore.hasNext()) {
				resultStore.next();
				Element element = root.addElement(tableName);
				for (String columnName : columnNameList) {
					Object object = resultStore.getColumnData(columnName);
					if(object == null) {
						element.addAttribute(columnName, "");
					} else {
						if (object instanceof Date) {
							element.addAttribute(columnName, DateUtils.dateToString(
									(Date) object, "yyyy-MM-dd HH:mm:ss"));
						} else {
							element.addAttribute(columnName, object.toString());
						}
					}
				}
			}
			return document;
		} catch(BussinessException e) {
			throw e;
		}
	}
	
	public static void outputData(Writer writer, ResultStore resultStore) throws BussinessException {
		try {
			Document document = generate(resultStore);
			OutputFormat outputFormat = new OutputFormat();
			outputFormat.setEncoding("UTF-8");
			outputFormat.setNewlines(true);
			outputFormat.setIndent(true);
			XMLWriter xmlWriter = new XMLWriter(writer, outputFormat);
			xmlWriter.write(document);
			xmlWriter.flush();
		} catch(IOException e) {
			throw new BussinessException(e);
		}
	}
	
	public static void effectiveOutputData(Writer writer, ResultStore resultStore) throws BussinessException {
		try {
			String tableName = resultStore.getTableName();
			List<String> columnNameList = resultStore.getColumnNames();
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
		} catch(IOException e) {
			throw new BussinessException(e);
		}
	}
}
