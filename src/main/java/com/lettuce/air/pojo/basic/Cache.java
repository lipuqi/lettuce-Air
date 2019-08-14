package com.lettuce.air.pojo.basic;

/**
 * 存储基类
 * @author Lando
 *
 */
public class Cache {

	//过期时间戳
	private Long expiresIn = null;
	
	public Long getExpiresIn() {
		return expiresIn;
	}
	
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
