package com.export.data.input;

import com.export.exception.BussinessException;
import com.export.utils.JdbcDriverUtils;

public class InputServiceFactory {
	
	public static InputService getInputService (
			SourceConfiguration sourceConfiguration) throws BussinessException {
		SourceType sourceType = sourceConfiguration.getSourceType();
		switch(sourceType) {
		case DB:
			JdbcDriverUtils.checkProperties(sourceConfiguration);
			return new DBInputService(sourceConfiguration);
		default:
			throw new BussinessException("Not found InputService of " + sourceType);
		}
	}
}
