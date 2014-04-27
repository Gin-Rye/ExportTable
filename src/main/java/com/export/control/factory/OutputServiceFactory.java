package com.export.control.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.export.base.exception.BussinessException;
import com.export.base.utils.SinkTypeMappingUtils;
import com.export.base.utils.SourceTypeMappingUtils;
import com.export.model.input.InputService;
import com.export.model.output.H2XmlFileOutputService;
import com.export.model.output.OutputService;
import com.export.model.configuration.Configuration;

public class OutputServiceFactory {
	
	public static OutputService getOutputService(
			Configuration sinkConfiguration) throws BussinessException {
		try {
			String sinkType = sinkConfiguration.getType();
			String outputServiceName = SinkTypeMappingUtils.getOutputServiceName(sinkType);
			Class<?> outputServiceClass = Class.forName(outputServiceName);
			Class[] paramTypes = {Configuration.class};
			Object[] params = {sinkConfiguration};
			Constructor outputServiceConstructor = outputServiceClass.getConstructor(paramTypes);
			OutputService outputService = (OutputService) outputServiceConstructor.newInstance(params);
			return outputService;
		} catch(ClassNotFoundException e) {
			throw new BussinessException(e);
		} catch(NoSuchMethodException e) {
			throw new BussinessException(e);
		} catch (InstantiationException e) {
			throw new BussinessException(e);
		} catch (IllegalAccessException e) {
			throw new BussinessException(e);
		} catch (IllegalArgumentException e) {
			throw new BussinessException(e);
		} catch (InvocationTargetException e) {
			throw new BussinessException(e);
		}
	}
}
