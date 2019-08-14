package com.lettuce.air.profile.data;

import net.sf.json.JSONObject;

/**
 * 数据上报基类
 * @author Lando
 *
 */
public abstract class DeviceData {
	
	//服务ID
	private String serviceId;
	
	//服务类型
	private String serviceType;
	
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public abstract void packaging(JSONObject service);
	
}
