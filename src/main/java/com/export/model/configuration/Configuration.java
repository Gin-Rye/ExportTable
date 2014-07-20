package com.export.model.configuration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.export.base.exception.BusinessException;

public abstract class Configuration {
	protected Map<String, String> extraProperties = new HashMap<String, String>();
	protected String type;
	
	public Configuration(String type) {
		this.type = type;
	}

	@Override
	public Configuration clone() {
		try {
			return (Configuration) super.clone();
		} catch(CloneNotSupportedException e) {
			RuntimeException exception = new RuntimeException(e);
			throw exception;
		}
	}
	
	public Map<String, String> getExtraProperties() {
		return extraProperties;
	}
	
	public String getType() {
		return type;
	}
	
	public void setProperty(String name, String value) throws BusinessException {
		try {
			Class<?> configurationClass = this.getClass();
			Method[] methods = configurationClass.getMethods();
			for(Method method : methods) {
				String methodName = method.getName();
				if(methodName.equalsIgnoreCase("set" + name)) {
					method.invoke(this, value);
				}
			}
		} catch(IllegalAccessException e) {
			throw new BusinessException(e);
		} catch(InvocationTargetException e) {
			throw new BusinessException(e);
		}
	}
}
