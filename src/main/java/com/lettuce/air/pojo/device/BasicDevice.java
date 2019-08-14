package com.lettuce.air.pojo.device;

import com.lettuce.air.pojo.basic.Cache;

/**
 * 基础设备类
 * @author Lando
 *
 */
public abstract class BasicDevice extends Cache{

	//设备状态
	private Integer status;
	
	//修改时间
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
