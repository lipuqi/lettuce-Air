package com.lettuce.air.profile.command;

import net.sf.json.JSONObject;

/**
 * 命令基类
 * @author Lando
 *
 */
public abstract class DeviceCommand {
	
	//服务ID
	private String serviceId;
	
	//服务类型
	private String serviceType;
	
	//命令内容
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
	
	/**
	 * 封装参数
	 * @return
	 */
	public abstract JSONObject unpack();
	
}
