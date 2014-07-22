package com.export.control.batch.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.export.base.exception.BusinessException;
import com.export.base.utils.SinkMappingUtils;
import com.export.model.output.OutputService;
import com.export.model.configuration.SinkConfiguration;

public class OutputServiceFactory {
	
	public static OutputService getOutputService(
			SinkConfiguration sinkConfiguration) throws BusinessException {
		try {
			String sinkType = sinkConfiguration.getType();
			String outputServiceName = SinkMappingUtils.getOutputServiceName(sinkType);
			Class<?> outputServiceClass = Class.forName(outputServiceName);
			Class<?>[] paramTypes = {sinkConfiguration.getClass()};
			Object[] params = {sinkConfiguration};
			Constructor<?> outputServiceConstructor = outputServiceClass.getConstructor(paramTypes);
			OutputService outputService = (OutputService) outputServiceConstructor.newInstance(params);
			return outputService;
		} catch(ClassNotFoundException e) {
			throw new BusinessException(e);
		} catch(NoSuchMethodException e) {
			throw new BusinessException(e);
		} catch (InstantiationException e) {
			throw new BusinessException(e);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (IllegalArgumentException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}
	}
}
