package com.lettuce.air.profile.command;

import net.sf.json.JSONObject;

public abstract class DeviceCommand {
	
	private String serviceId;
	
	private String serviceType;
	
	private String method;
	
	public DeviceCommand() {
	}
	
	public DeviceCommand(String serviceId, String serviceType, String method) {
		super();
		this.serviceId = serviceId;
		this.serviceType = serviceType;
		this.method = method;
	}

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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public abstract JSONObject unpack();
	
}
