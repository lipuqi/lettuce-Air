package com.puka.iot.app.profile.data;

import net.sf.json.JSONObject;

public abstract class DeviceData {
	
	private String serviceId;
	
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
