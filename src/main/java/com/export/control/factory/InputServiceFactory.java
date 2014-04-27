package com.export.control.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.export.base.exception.BussinessException;
import com.export.base.utils.SourceTypeMappingUtils;
import com.export.model.configuration.Configuration;
import com.export.model.input.InputService;

public class InputServiceFactory {
	
	public static InputService getInputService (
			Configuration sourceConfiguration) throws BussinessException {
		try {
			String sourceType = sourceConfiguration.getType();
			String inputServiceName = SourceTypeMappingUtils.getInputServiceName(sourceType);
			Class<?> inputServiceClass = Class.forName(inputServiceName);
			Class[] paramTypes = {Configuration.class};
			Object[] params = {sourceConfiguration};
			Constructor inputServiceConstructor = inputServiceClass.getConstructor(paramTypes);
			InputService inputService = (InputService) inputServiceConstructor.newInstance(params);
			return inputService;
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
