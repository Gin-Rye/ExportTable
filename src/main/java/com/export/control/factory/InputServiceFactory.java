package com.export.control.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.export.base.exception.BusinessException;
import com.export.base.utils.SourceMappingUtils;
import com.export.model.configuration.SourceConfiguration;
import com.export.model.input.InputService;
import com.export.model.input.query.QueryCommand;

public class InputServiceFactory {
	
	public static InputService<? extends QueryCommand> getInputService (
			SourceConfiguration sourceConfiguration) throws BusinessException {
		try {
			String sourceType = sourceConfiguration.getType();
			String inputServiceName = SourceMappingUtils.getInputServiceName(sourceType);
			Class<?> inputServiceClass = Class.forName(inputServiceName);
			Class<?>[] paramTypes = {sourceConfiguration.getClass()};
			Object[] params = {sourceConfiguration};
			Constructor<?> inputServiceConstructor = inputServiceClass.getConstructor(paramTypes);
			InputService<?> inputService = (InputService<?>) inputServiceConstructor.newInstance(params);
			return inputService;
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
