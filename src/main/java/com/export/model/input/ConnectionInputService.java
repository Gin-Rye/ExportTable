package com.export.model.input;

import com.export.base.exception.BussinessException;
import com.export.model.configuration.Configuration;
import com.export.model.store.ResultStore;

public abstract class ConnectionInputService extends InputService {
	
	public ConnectionInputService(Configuration sourceConfiguration) throws BussinessException {
		super(sourceConfiguration);
		connect();
	}

	public abstract void connect() throws BussinessException;
	
	public abstract void close() throws BussinessException;
	
	@Override
	public abstract ResultStore inputData(String command) throws BussinessException;
}
