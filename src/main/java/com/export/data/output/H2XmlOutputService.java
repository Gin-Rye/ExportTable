package com.export.data.output;

import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import com.export.data.store.ResultStore;
import com.export.exception.BussinessException;
import com.export.utils.DateUtils;

public abstract class H2XmlOutputService extends OutputService {

	public H2XmlOutputService(SinkConfiguration sinkConfiguration) {
		super(sinkConfiguration);
	}
	
	@Override
	public abstract void outputData(String tableName, ResultStore resultStore) throws BussinessException;
	
	@Override
	public abstract void effectiveOutputData(String tableName, ResultStore resultStore) throws BussinessException;

	public Document generateXmlDocument(String tableName, ResultStore resultStore) throws BussinessException {
		List<String> columnNameList = getColumnNames(resultStore);
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
	}
}
