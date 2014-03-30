package com.export.data.output;

import com.export.data.store.ResultStore;
import com.export.exception.BussinessException;

public interface FileWriterInterface {
	
	void outputToFile(String filePath, String tableName, ResultStore resultStore) throws BussinessException;
	
	void effectiveOutputToFile(String filePath, String tableName, ResultStore resultStore) throws BussinessException;
}
