package com.lettuce.air.common.exception;

/**
 * 基础异常类
 * @author Lando
 *
 */
public class BasicException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3014416203285717925L;

    private Integer code;
    
    private Exception exception;
    
	public BasicException(Integer code, Exception exception) {
		super();
		this.code = code;
		this.exception = exception;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
}
