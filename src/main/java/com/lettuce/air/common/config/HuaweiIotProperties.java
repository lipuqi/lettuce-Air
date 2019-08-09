package com.lettuce.air.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix="huaweiIot.config")
public class HuaweiIotProperties {
	
	private String appID;
	
	private String appSecret;
	
	private String deviceId;
	
	private Long commandExecuteTime;
	
	private String commandCallbackUrl;
	
	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Long getCommandExecuteTime() {
		return commandExecuteTime;
	}

	public void setCommandExecuteTime(Long commandExecuteTime) {
		this.commandExecuteTime = commandExecuteTime;
	}

	public String getCommandCallbackUrl() {
		return commandCallbackUrl;
	}

	public void setCommandCallbackUrl(String commandCallbackUrl) {
		this.commandCallbackUrl = commandCallbackUrl;
	}
	
}
