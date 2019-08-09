package com.lettuce.air.pojo.device;

import com.lettuce.air.pojo.basic.Cache;

public abstract class BasicDevice extends Cache{

	private Integer status;
	
	private Long updateTime;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime() {
		this.updateTime = System.currentTimeMillis();
	}

	public abstract String getDeviceKey();
	
}
