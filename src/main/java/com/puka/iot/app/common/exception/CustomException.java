package com.puka.iot.app.common.exception;

import org.apache.log4j.Logger;

/**
 * 基础异常类
 * 
 * @author Lando
 *
 */
public class CustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3014416203285717925L;

	private Logger LOGGER = null;

	@SuppressWarnings("rawtypes")
	private Class clazz;

	private String message;

	@SuppressWarnings("rawtypes")
	public CustomException(Class clazz, String message) {
		this.clazz = clazz;
		this.message = message;
		LOGGER = Logger.getLogger(clazz);
		LOGGER.info(message);
	}

	@SuppressWarnings("rawtypes")
	public CustomException(Class clazz, String message, Exception exception) {
		this.clazz = clazz;
		this.message = message;
		LOGGER = Logger.getLogger(clazz);
		LOGGER.info(message, exception);
	}

	@SuppressWarnings("rawtypes")
	public Class getClazz() {
		return clazz;
	}

	public void setClazz(@SuppressWarnings("rawtypes") Class clazz) {
		this.clazz = clazz;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
