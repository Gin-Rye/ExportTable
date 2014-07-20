package com.export.model.output.export;

import java.io.OutputStream;

import com.export.base.exception.BusinessException;
import com.export.model.store.ResultStore;

public abstract class Exporter {
	
	public abstract void export(OutputStream outputStream, ResultStore resultStore) throws BusinessException;
	
	public abstract void effectiveExport(OutputStream outputStream, ResultStore resultStore) throws BusinessException;
}
